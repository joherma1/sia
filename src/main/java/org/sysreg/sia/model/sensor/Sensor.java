package org.sysreg.sia.model.sensor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by jose on 08/02/14.
 */
public abstract class Sensor {

    private String id;
    private String code;
    private double value;

    private enum Units {}

    ;
    private Units units;
    private String description;

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
