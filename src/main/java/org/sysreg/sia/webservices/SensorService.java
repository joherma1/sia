package org.sysreg.sia.webservices;

import org.sysreg.sia.model.sensor.Sensor;
import org.sysreg.sia.webservices.BoardService;

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
