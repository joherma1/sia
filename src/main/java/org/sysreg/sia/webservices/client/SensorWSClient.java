package org.sysreg.sia.webservices.client;

import org.sysreg.sia.webservices.dto.SensorDTO;

import java.util.ArrayList;

/**
 * Created by joseant on 24/08/15.
 */
public interface SensorWSClient {

    int getPort();

    void setPort(int port);

    String getHost();

    void setHost(String host);

    ArrayList<SensorDTO> getSensors(String boardId);

    SensorDTO getSensor(String boardId, String sensorId);

    Float getSensorValue (String boardId, String sensorId);
}
