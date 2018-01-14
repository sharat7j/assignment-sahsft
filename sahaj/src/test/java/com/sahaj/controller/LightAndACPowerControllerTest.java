package com.sahaj.controller;

import com.sahaj.model.Floor;
import com.sahaj.model.Hotel;
import com.sahaj.model.Motion;
import com.sahaj.model.SubCorridor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class LightAndACPowerControllerTest {

    private LightAndACPowerController lightAndACPowerController;

    private Hotel hotel;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        Hotel.HotelBuilder builder = new Hotel.HotelBuilder();

        hotel = builder.withFloors(2)
                .withMainCorridors(2)
                .withSubCorridors(2).build();

        lightAndACPowerController = new LightAndACPowerController(hotel);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for
    LightAndACPowerController
     * .
     */
    @Test
    public void testLightAndACPowerController() {
        assertEquals(hotel, lightAndACPowerController.getHotel());
    }

    /**
     * Test method for
     * update method
     * .
     */
    @Test
    public void testUpdate() {
        lightAndACPowerController.update(new Motion(1, 2), true);
        assertTrue("Not switched on!", hotel.getFloors().get(0)
                .getSubCorridors().get(1).getLights().get(0).isSwitchedOn());
        lightAndACPowerController.update(new Motion(1, 2), true);
        assertTrue("Not switched on!", hotel.getFloors().get(0)
                .getSubCorridors().get(1).getLights().get(0).isSwitchedOn());
        lightAndACPowerController.update(new Motion(1, 2), false);
        assertFalse("Still switched on!", hotel.getFloors().get(0)
                .getSubCorridors().get(1).getLights().get(0).isSwitchedOn());
        lightAndACPowerController.update(new Motion(1, 2), false);
        assertFalse("Still switched on!", hotel.getFloors().get(0)
                .getSubCorridors().get(1).getLights().get(0).isSwitchedOn());

    }

    /**
     * Test method for
     *getMatchingFloor
     *
     */
    @Test
    public void testgetMatchingFloor() {
        Motion motion = new Motion(1, 1);
        Floor matchingFloor = lightAndACPowerController.getMatchingFloor(hotel,
                motion);
        assertTrue("Not the same floor", 1 == matchingFloor.getNumber());
        // Let's create a Motion in an invalid Floor.
        motion = new Motion(-1, 1);
        try {
            matchingFloor = lightAndACPowerController.getMatchingFloor(hotel,
                    motion);
            assertNull("Fake floor found!", matchingFloor);
        } catch (NoSuchElementException exception){
            // Expected. Ignored.
        }


    }

    /**
     * Test method for
     * getMatchingSubcorridor
     */
    @Test
    public void testgetMatchingSubCorridor() {
        Motion motion = new Motion(1, 2);
        Floor matchingFloor = lightAndACPowerController.getMatchingFloor(hotel,
                motion);
        SubCorridor matchingSubCorridor = lightAndACPowerController
                .getMatchingSubCorridor(motion, matchingFloor);
        assertTrue("Not the same floor",
                2 == matchingSubCorridor.getNumber());
    }


    /**
     * Test method for
     * findAsubcorridor
     */
    @Test
    public void testFindASubCorridor() {
        Motion motion = new Motion(1, 2);
        Floor matchingFloor = lightAndACPowerController.getMatchingFloor(hotel,
                motion);
        SubCorridor matchingSubCorridor = lightAndACPowerController
                .getMatchingSubCorridor(motion, matchingFloor);
        SubCorridor someOtherSubCorridor = lightAndACPowerController
                .findASubCorridor(motion, matchingFloor,
                        matchingSubCorridor);
        assertFalse("It's the same SubCorridor",
                2 == someOtherSubCorridor.getNumber());
        // Let's remove the only other SubCorridor and see!
        SubCorridor removedSubCorridor = hotel.getFloors().get(0)
                .getSubCorridors().remove(0);
        someOtherSubCorridor = lightAndACPowerController.findASubCorridor(
                motion, matchingFloor, matchingSubCorridor);
        assertTrue("It's the same SubCorridor",
                2 == someOtherSubCorridor.getNumber());
        // Let's add it back. Our test is successful. It has to be, otherwise it
        // can't be executing this line!
        hotel.getFloors().get(0).getSubCorridors().add(removedSubCorridor);
    }



}
