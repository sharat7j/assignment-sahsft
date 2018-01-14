package com.sahaj.controller;

import com.sahaj.criteria.PowerCriteria;
import com.sahaj.model.Floor;
import com.sahaj.model.Hotel;
import com.sahaj.model.Motion;
import com.sahaj.model.SubCorridor;

import java.util.*;

public class LightAndACPowerController implements Observer {


    private Hotel hotel;
    private Map<SubCorridor, SubCorridor> subCorridorMap;

    public LightAndACPowerController(Hotel hotel) {
        this.hotel = hotel;
        subCorridorMap = new HashMap<>();

    }

    @Override
    public void update(Observable o, Object arg) {
        Motion motion = (Motion) o;
        controlSubCorridorsLights(motion, (Boolean) arg);
    }

    private void controlSubCorridorsLights(Motion motion, boolean isOn) {
        Floor correspondingFloor = getMatchingFloor(hotel, motion);
        SubCorridor correspondingSubCorridor =getMatchingSubCorridor(motion, correspondingFloor);
        // No change in event if on
        if (correspondingSubCorridor.getLights().get(0).isSwitchedOn() == isOn) {
            return;
        }

        correspondingSubCorridor.getLights().forEach(
                light -> light.setSwitchedOn(isOn));


        // make decision  if the power consumption exceeds then find any subCorridor and
        // turn off its AC.
        decisionOperation(motion,correspondingFloor,correspondingSubCorridor);



    }


    private void decisionOperation(Motion motion,Floor floor,SubCorridor subcorridor){
        PowerCriteria powerCriteria = new PowerCriteria();
        //if power  exceeded
        if (!powerCriteria.isCriteriaMet(floor)) {
            SubCorridor otherSubCorridor = findASubCorridor(motion, floor,
                    subcorridor);
            otherSubCorridor.getAirConditioners().get(0).setSwitchedOn(false);


            subCorridorMap.put(subcorridor,
                    otherSubCorridor);
        } else {
            // turn the AC back on, which was turned off previously

            SubCorridor otherSubCorridor = subCorridorMap
                    .get(subcorridor);
            otherSubCorridor.getAirConditioners().get(0).setSwitchedOn(true);
        }

    }

    public Map<SubCorridor, SubCorridor> getSubCorridorMap() {
        return subCorridorMap;
    }



    public SubCorridor findASubCorridor(Motion motion,Floor floor,SubCorridor subCorridor){
        SubCorridor otherSubCorridor=null;
        try {
            List<SubCorridor> subCorridorList = floor.getSubCorridors();

            for (SubCorridor eachSubCorridor : subCorridorList) {
                if (eachSubCorridor.getNumber() != motion.getSubCorridorNumber()) {
                    otherSubCorridor = eachSubCorridor;
                }
            }
            if (otherSubCorridor == null) {
                otherSubCorridor = subCorridor;
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
       return otherSubCorridor;

    }


    /**
     * @return  hotel
     */
    public Hotel getHotel() {
        return hotel;
    }

    public Floor getMatchingFloor(Hotel hotel,Motion motion){
        List<Floor> floors=hotel
                .getFloors();

        for(Floor floor:floors){
            if(floor.getNumber()==motion.getFloorNumber()){
                return  floor;
            }
        }
        return  null;
    }


    public SubCorridor getMatchingSubCorridor(Motion motion,
                                               Floor currentfloor) {


        List<SubCorridor> subCorridors=currentfloor.getSubCorridors();

        for(SubCorridor subCorridor:subCorridors){
            if(subCorridor.getNumber()==motion.getSubCorridorNumber()){
                return subCorridor;

            }
        }
        return null;

    }

}
