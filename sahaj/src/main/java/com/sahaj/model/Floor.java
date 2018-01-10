package com.sahaj.model;


import java.util.List;

public class Floor {


    private int number;
    private List<MainCorridor> mainCorridors;
    private List<SubCorridor> subCorridors;
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<MainCorridor> getMainCorridors() {
        return mainCorridors;
    }

    public void setMainCorridors(List<MainCorridor> mainCorridors) {
        this.mainCorridors = mainCorridors;
    }

    public List<SubCorridor> getSubCorridors() {
        return subCorridors;
    }

    public void setSubCorridors(List<SubCorridor> subCorridors) {
        this.subCorridors = subCorridors;
    }

//override toString to print floor number starting from 1
    @Override
    public String toString() {
        return "Floor " + (number + 1);
    }

}
