package org.sysreg.sia.model.sensor;

import javax.persistence.*;

/**
 * Created by jose on 08/02/14.
 */
@Entity
@DiscriminatorValue("HUMIDITY")
public class Humidity extends Sensor {

    public Humidity(){
        this.setUnits(Units.PERCENT);
    }

    public Humidity(String id, String code){
        this.setUnits(Units.PERCENT);
        this.setId(id);
        this.setCode(code);
    }
}
