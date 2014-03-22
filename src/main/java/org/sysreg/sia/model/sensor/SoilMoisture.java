package org.sysreg.sia.model.sensor;

import javax.persistence.*;

/**
 * Created by jose on 08/02/14.
 */

@Entity
@DiscriminatorValue("SOIL_MOISTURE")
public class SoilMoisture extends Humidity {

    public SoilMoisture(String id){
        this.setCode("sysreg.sia.SoilMoisture");
        this.setId(id);
    }

    public SoilMoisture() {
        this.setCode("sysreg.sia.SoilMoisture");
    }
}
