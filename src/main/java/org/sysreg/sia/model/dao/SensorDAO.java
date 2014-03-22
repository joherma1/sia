package org.sysreg.sia.model.dao;

import org.sysreg.sia.model.sensor.Sensor;

public interface SensorDAO {

    Sensor findById(String id);

	void persist(Sensor sensor);

}
