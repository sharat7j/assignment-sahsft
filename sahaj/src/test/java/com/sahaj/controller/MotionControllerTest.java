package com.sahaj.controller;

import com.sahaj.model.Hotel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MotionControllerTest {


    private MotionController motionController;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        // Nothing to do. Individual tests will ensure the creation.
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        motionController = null;
    }

    /**
     * Test method for
     * MotionController
     * .
     */
    @Test
    public void testMotionController() {
        motionController = new MotionController(1, 1, new LightAndACPowerController(
                new Hotel.HotelBuilder().build()));
        assertNotNull("Motion is not generated!", motionController.getMotion());
        assertTrue("Floor number is incorrect!", motionController.getMotion()
                .getFloorNumber() == 1);
        assertTrue("SubCorridor number is incorrect!", motionController
                .getMotion().getSubCorridorNumber() == 1);
    }

    /**
     * Test method for
     * raiseMotionDetectedEvent
     * .
     */
    @Test
    public void testRaiseMotionDetectedEvent() {
        Hotel hotel = new Hotel.HotelBuilder().withFloors(2)
                .withMainCorridors(2).withSubCorridors(2).build();
        motionController = new MotionController(1, 1,
                new LightAndACPowerController(hotel));
        motionController.raiseMotionDetectedEvent(true);
        assertTrue("Not switched on!", hotel.getFloors().get(0)
                .getSubCorridors().get(0).getLights().get(0).isSwitchedOn());
        assertFalse("Accidentally switched on!", hotel.getFloors().get(1)
                .getSubCorridors().get(0).getLights().get(0).isSwitchedOn());
        motionController.raiseMotionDetectedEvent(false);
        assertFalse("Still switched on!", hotel.getFloors().get(0)
                .getSubCorridors().get(0).getLights().get(0).isSwitchedOn());
    }
}
