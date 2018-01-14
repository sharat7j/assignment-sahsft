package com.sahaj.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Light {

    private boolean isSwitchedOn;

    private int id;
    private int units;


    public Light(int id ,int units,boolean isSwitchedOn){
        this.id=id+1;

        this.units=units;
        this.isSwitchedOn=isSwitchedOn;

    }

    public boolean isSwitchedOn() {
        return isSwitchedOn;
    }

    public void setSwitchedOn(boolean switchedOn) {
        isSwitchedOn = switchedOn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }
    @Override
    public String toString() {
        return  "    Light "
                + (id) + " : " + (isSwitchedOn ? "ON" : "OFF");
    }
}
