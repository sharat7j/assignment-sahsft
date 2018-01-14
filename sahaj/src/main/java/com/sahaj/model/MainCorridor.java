package com.sahaj.model;

public class MainCorridor extends Corridor {
    private int number;
    private String type;
    public MainCorridor(int number) {
        super("MAIN");
        this.number=number+1;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString()
    {
        return  "  Main corridor " + number ;
    }
}
