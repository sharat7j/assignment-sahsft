package com.sahaj.model;

import com.sahaj.builder.HotelBuilder;

import java.util.List;

public class Hotel {

    private List<Floor> floors;

    private String name;





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



}
