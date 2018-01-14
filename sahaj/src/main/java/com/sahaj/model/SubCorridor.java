package com.sahaj.model;

public class SubCorridor extends Corridor{
    private int number;
    private boolean motionDetected;
    public SubCorridor(int number) {
        super("SUB");
        this.number=number+1;

    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isMotionDetected() {
        return motionDetected;
    }

    public void setMotionDetected(boolean motionDetected) {
        this.motionDetected = motionDetected;
    }

    @Override
    public String toString() {
        return  "  Sub corridor " + number ;
    }

}
