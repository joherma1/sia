package org.sysreg.sia.model.sensor;

import org.sysreg.sia.model.Enclosure;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * Created by jose on 08/02/14.
 */
@Entity
@Table(name = "SENSORS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "SENSOR_TYPE")
@DiscriminatorValue("SENSOR")
public class Sensor implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Column
    private String code;
    @Column
    private double value;

    protected enum Units {CELSIUS, FAHRENHEIT, KELVIN, PASCAL, PERCENT};
    @Column
    @Enumerated(EnumType.STRING)
    private Units units;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Units getUnits() {
        return units;
    }

    public void setUnits(Units units) {
        this.units = units;
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

    static String printAttributes(Class<?> clazz, Sensor sensor) {
        String res = "";
        try {
            if (clazz.equals(Sensor.class)) {
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
