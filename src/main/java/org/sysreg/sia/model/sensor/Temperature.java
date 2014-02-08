package org.sysreg.sia.model.sensor;

/**
 * Created by jose on 08/02/14.
 */
public class Temperature extends Sensor{

    public Temperature() {
    }

    private enum Units{CELSIUS, FAHRENHEIT, KELVIN};
    private Units units = Units.CELSIUS;

    public Temperature(String id, String code){
        this.setId(id);
        this.setCode(code);
    }
}
