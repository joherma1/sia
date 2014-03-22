package org.sysreg.sia.model.sensor;

import javax.persistence.*;

/**
 * Created by jose on 08/02/14.
 */
@Entity
@DiscriminatorValue("HUMIDITY")
@AttributeOverrides({
    @AttributeOverride(name="units", column = @Column(name="HUMIDITY_UNITS"))
})
public class Humidity extends Sensor {

    private enum Units {PERCENT};
    @Enumerated(EnumType.STRING)
    private Units units = Units.PERCENT;

    public Humidity(){

    }

    public Humidity(String id, String code){
        this.setId(id);
        this.setCode(code);
    }
}
