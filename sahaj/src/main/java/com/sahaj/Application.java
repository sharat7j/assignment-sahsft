package com.sahaj;

import com.sahaj.constants.Constants;
import com.sahaj.controller.LightAndACPowerController;
import com.sahaj.controller.MotionController;
import com.sahaj.model.Hotel;
import org.apache.commons.lang3.StringUtils;
import java.util.Scanner;

public class Application {

    private static final long SENSOR_INPUT_READ_INTERVAL = 5000;
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(Constants.INPUT_FLOOR_COUNT);
        throwExceptionForInvalidValue();
        int floorCount = Integer.parseInt(SCANNER.nextLine());
        System.out.println(Constants.INPUT_MAIN_CORRIDOR_COUNT);
        throwExceptionForInvalidValue();
        int mainCorridorCount = Integer.parseInt(SCANNER.nextLine());
        System.out.println(Constants.SUB_CORRIDOR_COUNT);
        throwExceptionForInvalidValue();
        int subCorridorCount = Integer.parseInt(SCANNER.nextLine());
        Hotel.HotelBuilder builder = new Hotel.HotelBuilder();
        Hotel hotel = builder.withFloors(floorCount)
                .withMainCorridors(mainCorridorCount)
                .withSubCorridors(subCorridorCount).build();
        LightAndACPowerController lightAndACPowerController = new LightAndACPowerController(hotel);

        //default state
        System.out.println(hotel);


        //run infinitetly until exit
        while(true){
            try {
                String input = SCANNER.nextLine();
                if (input.isEmpty()) {
                    throw new IllegalArgumentException(Constants.MALFORMED_FORMAT);
                } else if (input.equalsIgnoreCase(Constants.EXIT)) {
                    SCANNER.close();
                    System.out.println("Exit");
                    System.exit(0);
                }
                processAndTakeAction(input,hotel,lightAndACPowerController);

            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }



    }

    private static void processAndTakeAction(String input,Hotel hotel,LightAndACPowerController lightAndACPowerController){
        String floorNumberString="";
        String subCorridorNumberString="";
        boolean turnOnLightSwitch=false;
        try {

            if (input.startsWith(Constants.MOVEMENT_PREFIX) && input.split(",").length == 2 && input.split(",")[1].startsWith(Constants.SUBCORRIDOR_PREFIX)) {
                String[] split = input.split(",");
                floorNumberString = split[0].substring(Constants.MOVEMENT_PREFIX.length()).trim();

                subCorridorNumberString = split[1].substring(Constants.SUBCORRIDOR_PREFIX.length()).trim();

                if (StringUtils.isBlank(floorNumberString) || StringUtils.isBlank(subCorridorNumberString)) {
                    throwExceptionForInvalidValue();
                }
                turnOnLightSwitch = true;
            }
            if (input.startsWith(Constants.NO_MOVEMENT_PREFIX) && input.split(",").length == 2 && input.split(",")[1].startsWith(Constants.SUBCORRIDOR_PREFIX)) {

                String[] split = input.split(",");
                floorNumberString = split[0].substring((Constants.NO_MOVEMENT_PREFIX + " Floor").length()).trim();
                subCorridorNumberString = split[1].substring(Constants.SUBCORRIDOR_PREFIX.length(),Constants.SUBCORRIDOR_PREFIX.length()+2).trim();

                if (StringUtils.isBlank(floorNumberString) || StringUtils.isBlank(subCorridorNumberString)) {
                    throwExceptionForInvalidValue();
                }

                turnOnLightSwitch = false;
            }

            if (!StringUtils.isBlank(floorNumberString) && !StringUtils.isBlank(floorNumberString)) {
                MotionController motionController = new MotionController(
                        Integer.parseInt(floorNumberString), Integer.parseInt(subCorridorNumberString),
                        lightAndACPowerController);

                motionController.raiseMotionDetectedEvent(turnOnLightSwitch);

                System.out.println(hotel);
                Thread.sleep(SENSOR_INPUT_READ_INTERVAL);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    private static void throwExceptionForInvalidValue() {
        while (!SCANNER.hasNextInt()) {
            System.out.println(Constants.INVALID_INTEGER);
            SCANNER.nextLine();
        }
    }
}
