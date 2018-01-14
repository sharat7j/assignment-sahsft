package com.sahaj.model;

import com.sahaj.constants.Constants;

import java.util.ArrayList;
import java.util.List;

public final class Hotel {

    private List<Floor> floors;

    private String name;

    public Hotel(HotelBuilder builder) {

            this.name=builder.name;
            this.floors=builder.floors;
    }



    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        getFloors().forEach(floor -> {
            stringBuilder.append(floor.toString());
            stringBuilder.append(System.lineSeparator());
            floor.getMainCorridors().forEach(mainCorridor -> {
                stringBuilder.append(mainCorridor.toString());
                stringBuilder.append(System.lineSeparator());
                mainCorridor.getLights().forEach(lightBulb -> {
                    stringBuilder.append(lightBulb.toString());
                    stringBuilder.append(System.lineSeparator());
                });
                mainCorridor.getAirConditioners().forEach(airConditioner -> {
                    stringBuilder.append(airConditioner.toString());
                    stringBuilder.append(System.lineSeparator());
                });
            });
            floor.getSubCorridors().forEach(subCorridor -> {
                stringBuilder.append(subCorridor.toString());
                stringBuilder.append(System.lineSeparator());
                subCorridor.getLights().forEach(lightBulb -> {
                    stringBuilder.append(lightBulb.toString());
                    stringBuilder.append(System.lineSeparator());
                });
                subCorridor.getAirConditioners().forEach(airConditioner -> {
                    stringBuilder.append((airConditioner).toString());
                    stringBuilder.append(System.lineSeparator());
                });
            });
        });
        return stringBuilder.toString();
    }

    public static class HotelBuilder{
        private List<Floor> floors;

        private String name;

        public HotelBuilder(){
                name= Constants.HOTEL;
                floors=new ArrayList<>();
        }

        public Hotel build() {

            return new Hotel(this);
        }

        public HotelBuilder withFloors(int floorcount ){
            for(int i=0;i<floorcount;i++){
                Floor floor = new Floor(i);
                floor.setMainCorridors(new ArrayList<MainCorridor>());
                floor.setSubCorridors(new ArrayList<SubCorridor>());
                floors.add(floor);
            }
            return this;
        }


        public HotelBuilder withMainCorridors(int mainCorridorsCount){
            for (int i = 0; i < floors.size(); i++) {
                for (int j = 0; j < mainCorridorsCount; j++) {
                    MainCorridor mainCorridor = new MainCorridor(j);
                    floors.get(i).getMainCorridors()
                            .add(mainCorridor);
                }
            }
            return this;
        }

        public HotelBuilder withSubCorridors(int subCorridorsCount){
            for (int i = 0; i < floors.size(); i++) {
                for (int j = 0; j < subCorridorsCount; j++) {
                    SubCorridor subCorridor = new SubCorridor(j);
                    floors.get(i).getSubCorridors()
                            .add(subCorridor);
                }
            }
            return this;
        }




    }



}
