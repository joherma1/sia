package org.sysreg.sia.ws.client;

import org.sysreg.sia.ws.client.dto.AlRegSensorDTO;

import java.util.ArrayList;

/**
 * Created by joseant on 24/08/15.
 */
public interface SensorWSClient {

    int getPort();

    void setPort(int port);

    String getHost();

    void setHost(String host);

    ArrayList<AlRegSensorDTO> getSensors(String boardId);

    AlRegSensorDTO getSensor(String boardId, String sensorId);

    Float getSensorValue(String boardId, String sensorId);
}
