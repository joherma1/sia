package org.sysreg.sia.model.actuator;

import org.sysreg.sia.model.Enclosure;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * Created by jose on 08/02/14.
 */
@Entity
@Table(name = "ACTUATORS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ACTUATOR_TYPE")
@DiscriminatorValue("ACTUATOR")
public class Actuator implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Column
    private boolean enabled;

    @Column
    private String description;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "TOWN_ID", referencedColumnName = "TOWN_ID"),
            @JoinColumn(name = "AGGREGATE", referencedColumnName = "AGGREGATE"),
            @JoinColumn(name = "ZONE", referencedColumnName = "ZONE"),
            @JoinColumn(name = "POLYGON", referencedColumnName = "POLYGON"),
            @JoinColumn(name = "PARCEL", referencedColumnName = "PARCEL"),
            @JoinColumn(name = "ENCLOSURE", referencedColumnName = "ENCLOSURE"),
    })
    private Enclosure enclosure;

    public Actuator() {
    }

    public Actuator(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Enclosure getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(Enclosure enclosure) {
        this.enclosure = enclosure;
    }

    public String toString() {
            String res = "";
            Class sensorType = this.getClass();
            res += sensorType.getSimpleName() + ": " + '\t';
            res += printAttributes(sensorType, this);
            return res;
    }

    static String printAttributes(Class<?> clazz, Actuator sensor) {
        String res = "";
        try {
            if (clazz.equals(Actuator.class)) {
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    res += field.getName() + ": " + field.get(sensor) + '\t';
                }
                return res;
            } else {
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    res += field.getName() + ": " + field.get(sensor) + '\t';
                }
                return printAttributes(clazz.getSuperclass(), sensor) + '\t' + res;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
