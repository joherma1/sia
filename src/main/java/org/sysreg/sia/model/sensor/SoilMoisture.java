package org.sysreg.sia.model.sensor;

/**
 * Created by jose on 08/02/14.
 */
public class SoilMoisture extends Humidity {
    private final String code ="sysreg.sia.SoilMoisture";

    public SoilMoisture(String id){
        this.setId(id);
    }
}
