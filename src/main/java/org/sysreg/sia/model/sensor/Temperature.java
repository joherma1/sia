package org.sysreg.sia.model.sensor;


import javax.persistence.*;

/**
 * Created by jose on 08/02/14.
 */
@Entity
@DiscriminatorValue("TEMPERATURE")
public class Temperature extends Sensor{

    public Temperature() {
        this.setUnits(Units.CELSIUS);
    }

    public Temperature(String id, String code){
        this.setUnits(Units.CELSIUS);
        this.setId(id);
        this.setCode(code);
    }
}
