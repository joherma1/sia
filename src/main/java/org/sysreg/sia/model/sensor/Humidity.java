package org.sysreg.sia.model.sensor;

/**
 * Created by jose on 08/02/14.
 */
public class Humidity extends Sensor {
    private enum Units {PERCENT};
    private Units units = Units.PERCENT;

    public Humidity(){

    }

    public Humidity(String id, String code){
        this.setId(id);
        this.setCode(code);
    }
}
