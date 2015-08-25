package org.sysreg.sia.ws.service;

import org.sysreg.sia.model.sensor.Sensor;

import java.util.List;

/**
 * Created by joseant on 20/07/15.
 */
public interface SensorService {

    BoardService boardService = null;

    List<Sensor> getSensors();

    Sensor getSensor();

    Float getSensorValue();
}
