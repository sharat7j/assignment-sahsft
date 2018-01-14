package com.sahaj.model;

import java.util.concurrent.atomic.AtomicInteger;

public class AirConditioner {

    private boolean isSwitchedOn;

    private int id;
    private int units;

    public AirConditioner(int id,boolean isSwitchedOn, int units) {
        this.isSwitchedOn = isSwitchedOn;
        this.id = id+1;
        this.units = units;
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
        return "    AC  :"
                + (isSwitchedOn ? "ON" : "OFF");
    }
}
