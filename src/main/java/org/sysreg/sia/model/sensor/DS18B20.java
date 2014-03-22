package org.sysreg.sia.model.sensor;

import javax.persistence.*;

/**
 * Created by jose on 08/02/14.
 */
@Entity
@DiscriminatorValue("TEMPERATURE_DS18B20")
public class DS18B20 extends Temperature {

    public DS18B20(String id){
        this.setCode("DS18B20");
        this.setId(id);
    }

    public DS18B20() {
        this.setCode("DS18B20");
    }
}
