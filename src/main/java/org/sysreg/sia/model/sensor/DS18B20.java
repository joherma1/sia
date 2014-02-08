package org.sysreg.sia.model.sensor;

/**
 * Created by jose on 08/02/14.
 */
public class DS18B20 extends Temperature {
    private final String code = "DS18B20";

    public DS18B20(String id){
        this.setId(id);
    }


}
