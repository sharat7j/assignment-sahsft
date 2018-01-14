package com.sahaj.model;

import com.sahaj.constants.Constants;
import com.sahaj.criteria.PowerCriteria;

import java.util.ArrayList;
import java.util.List;

public  class Corridor {

    private List<Light> lights;

    private List<AirConditioner> airConditioners;

    public Corridor(){

    }

public Corridor(String type){
    lights = new ArrayList<>();

    lights.add(new Light(lights
            .size(),
            PowerCriteria.LIGHTUNITS,type.equalsIgnoreCase(Constants.MAIN) ));
    airConditioners = new ArrayList<>();
    airConditioners.add(new AirConditioner(airConditioners.size(),true,PowerCriteria.ACUNITS ));

}
    public List<Light> getLights() {
        return lights;
    }

    public void addLights(List<Light> lights) {
        this.lights.addAll(lights);
    }

    public List<AirConditioner> getAirConditioners() {
        return airConditioners;
    }

    public void addConditioners(List<AirConditioner> airConditioners) {
        this.airConditioners.addAll(airConditioners);
    }



}
