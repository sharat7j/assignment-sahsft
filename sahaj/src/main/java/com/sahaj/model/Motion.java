package com.sahaj.model;

import java.util.Observable;
import java.util.Observer;

public class Motion extends Observable{
    private int floorNumber;

    private int subCorridorNumber;

    public Motion(int floorNumber, int subCorridorNumber) {
        this.floorNumber = floorNumber;
        this.subCorridorNumber = subCorridorNumber;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public int getSubCorridorNumber() {
        return subCorridorNumber;
    }

    public void setSubCorridorNumber(int subCorridorNumber) {
        this.subCorridorNumber = subCorridorNumber;
    }

    @Override
    public synchronized void addObserver(Observer observer) {
        super.addObserver(observer);
    }


    @Override
    public void notifyObservers(Object object) {
        setChanged();
        super.notifyObservers(object);
    }


}
