package com.sahaj.controller;

import com.sahaj.model.Motion;

public class MotionController {




    private Motion motion;

    private LightAndACPowerController lightAndACPowerController;

    /**
     * Default Constructor.
     */
    public MotionController(int floorNumber, int subCorridorNumber,
                            LightAndACPowerController powerController) {
        motion = new Motion(floorNumber, subCorridorNumber);
        this.lightAndACPowerController = powerController;
    }

    /**
     * @return
     */
    public Motion getMotion() {

        return motion;
    }

    /**
     * Raises an event that a Motion has been detected to all the Observers.
     *
     * @param turnOnSwitch
     *            Turn on or not.
     */
    public void raiseMotionDetectedEvent(boolean turnOnSwitch) {
        motion.addObserver(lightAndACPowerController);
        motion.notifyObservers(turnOnSwitch);
    }




}
