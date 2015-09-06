package org.sysreg.sia.daos;

import org.sysreg.sia.model.sensor.Sensor;

public interface SensorDAO {

    Sensor findById(String id);

	void persist(Sensor sensor);

}
