package org.sysreg.sia.model.actuator;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by jose on 01/04/14.
 */
@Entity
@DiscriminatorValue("BASIC_ACTUATOR")
public class BasicActuator extends Actuator {

    public BasicActuator() {
    }

    public BasicActuator(String id) {
        super(id);
    }

    public void on(){
        System.out.println("---> Sensor: " + this.getId() + " [ON] <---");
    }

    public void off(){
        System.out.println("---> Sensor: " + this.getId() + " [OFF] <---");
    }
}
