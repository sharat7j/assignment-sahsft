package com.sahaj.criteria;

import com.sahaj.model.*;

import java.util.List;

public class PowerCriteria implements  Criteria{
    public static final int LIGHTUNITS=5;
    public static final int ACUNITS=10;

    /**
     *
     * @param floor
     * @return
     */
    @Override
    public boolean isCriteriaMet(Floor floor) {
        int floorConsumption=getFloorConsumption(floor);
        int maxConsumption=maxConsumption(floor);

        return floorConsumption<=maxConsumption;

    }

    private int maxConsumption(Floor floor) {
        List<MainCorridor> mainCorridors = floor.getMainCorridors();
        List<SubCorridor> subCorridors = floor.getSubCorridors();
        return (mainCorridors.size() * 15) + subCorridors.size() * 10;
    }

    private int getFloorConsumption(Floor floor){

        List<MainCorridor> mainCorridors = floor.getMainCorridors();

        List<SubCorridor> subCorridors = floor.getSubCorridors();

        int mainCorridorLightConsumption=0;
        int subCorridorLightConsumption=0;
        int mainCorridorAcConsumption=0;
        int subCorridorAcConsumption=0;
         for(MainCorridor mainCorridor:mainCorridors){

             List<Light> lightList=mainCorridor.getLights();
             for(Light light:lightList){
                 if(light.isSwitchedOn()){
                     mainCorridorLightConsumption+=LIGHTUNITS;
                 }
             }

             List<AirConditioner> acList=mainCorridor.getAirConditioners();
             for(AirConditioner ac:acList){
                 if(ac.isSwitchedOn()){
                     mainCorridorAcConsumption+=ACUNITS;
                 }
             }

        }



        for(SubCorridor subCorridor:subCorridors){

            List<Light> lightList=subCorridor.getLights();
            for(Light light:lightList){
                if(light.isSwitchedOn()){
                    subCorridorLightConsumption+=LIGHTUNITS;
                }
            }

            List<AirConditioner> acList=subCorridor.getAirConditioners();
            for(AirConditioner ac:acList){
                if(ac.isSwitchedOn()){
                    subCorridorAcConsumption+=ACUNITS;
                }
            }

        }

        return mainCorridorLightConsumption+mainCorridorAcConsumption+subCorridorLightConsumption+subCorridorAcConsumption;


    }


}
