package org.sysreg.sia.model.sensor;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.sysreg.sia.model.Board;
import org.sysreg.sia.model.Enclosure;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

/**
 * Created by jose on 08/02/14.
 */
@Entity
@Table(name = "SENSORS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "SENSOR_TYPE")
@DiscriminatorValue("SENSOR")
public class Sensor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Column
    private String code;

    @Column
    private Date timestamp;

    @Column
    private double value;

    @Column
    @Enumerated(EnumType.STRING)
    private Units units;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "BOARD_ID")
    private Board board;

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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
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

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
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

    // Override hashCode and equals to compare only with
    // id + code + description
    //  it doesn't matter which Board, units, value or timestamp have
    public int hashCode() {
        return new HashCodeBuilder(17, 31)
                . // two randomly chosen prime numbers
                        // if deriving: appendSuper(super.hashCode()).
                        append(this.id).append(this.code).append(this.description).toHashCode();
    }

    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof Sensor))
            return false;

        Sensor rhs = (Sensor) obj;
        return new EqualsBuilder()
                .
                        // if deriving: appendSuper(super.equals(obj)).
                                append(this.id, rhs.getId()).append(this.code, rhs.getCode())
                .append(this.description, rhs.getDescription()).isEquals();
    }
}
