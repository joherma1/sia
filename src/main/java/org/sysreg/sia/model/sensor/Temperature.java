package org.sysreg.sia.model.sensor;


import javax.persistence.*;

/**
 * Created by jose on 08/02/14.
 */
@Entity
@DiscriminatorValue("TEMPERATURE")
@AttributeOverrides({
        @AttributeOverride(name="units", column = @Column(name="TEMPERATURE_UNITS"))
})
public class Temperature extends Sensor{

    private enum Units{CELSIUS, FAHRENHEIT, KELVIN};
    @Enumerated(EnumType.STRING)
    private Units units = Units.CELSIUS;

    public Temperature() {
    }

    public Temperature(String id, String code){
        this.setId(id);
        this.setCode(code);
    }
}
