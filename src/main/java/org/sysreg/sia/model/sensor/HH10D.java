package org.sysreg.sia.model.sensor;

import javax.persistence.*;

/**
 * Created by jose on 08/02/14.
 */
@Entity
@DiscriminatorValue("HUMIDITY_HH10D")
public class HH10D extends Humidity {

    public HH10D (String id){
        this.setCode("HH10D");
        this.setId(id);
    }

    public HH10D() {
        this.setCode("HH10D");
    }
}
