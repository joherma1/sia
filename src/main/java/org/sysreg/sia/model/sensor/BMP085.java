package org.sysreg.sia.model.sensor;

/**
 * Created by jose on 08/02/14.
 */
public class BMP085 extends Pressure {

    private final String code = "BMP085";

    public BMP085(String id){
        this.setId(id);
    }
}
