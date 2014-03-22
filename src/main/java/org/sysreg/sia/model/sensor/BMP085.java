package org.sysreg.sia.model.sensor;

import javax.persistence.*;

/**
 * Created by jose on 08/02/14.
 */
@Entity
@DiscriminatorValue("PRESSURE_BMP085")
public class BMP085 extends Pressure {

    public BMP085(String id){
        this.setCode("BMP085");
        this.setId(id);
    }

    public BMP085() { this.setCode("BMP085"); }
}
