package com.sahaj.criteria;

import com.sahaj.controller.LightAndACPowerControllerTest;
import com.sahaj.model.Floor;
import com.sahaj.model.Hotel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PowerCriteriaTest {


    private PowerCriteria powerCriteria;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        powerCriteria = new PowerCriteria();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method isCriteriaMet(Floor)
     */
    @Test
    public void testisCriteriaMetFor() {
        Hotel hotel = new Hotel.HotelBuilder().withFloors(1).withMainCorridors(1).withSubCorridors(1).build();
        Floor floor = hotel.getFloors().get(0);
        assertTrue("Criteria is not met!", powerCriteria.isCriteriaMet(floor));
        floor.getSubCorridors().get(0).getAirConditioners().get(0).setSwitchedOn(true);
        floor.getSubCorridors().get(0).getLights().get(0).setSwitchedOn(true);
        powerCriteria.isCriteriaMet(floor);
        assertFalse("Criteria is  met!", powerCriteria.isCriteriaMet(floor));
    }

}
