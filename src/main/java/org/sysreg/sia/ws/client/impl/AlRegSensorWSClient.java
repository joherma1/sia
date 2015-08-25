package org.sysreg.sia.ws.client.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.client.RestTemplate;
import org.sysreg.sia.ws.client.SensorWSClient;
import org.sysreg.sia.ws.client.dto.AlRegSensorDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by joseant on 20/07/15.
 */
public class AlRegSensorWSClient implements SensorWSClient {
    private static final Logger log = LogManager.getLogger(AlRegSensorWSClient.class);

    RestTemplate springWSClient;

    private String host;

    private int port;


    public AlRegSensorWSClient() {
    }

    public AlRegSensorWSClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public void setPort(int port) {
        this.port = port;
    }

    public RestTemplate getSpringWSClient() {
        return springWSClient;
    }

    public void setSpringWSClient(RestTemplate springWSClient) {
        this.springWSClient = springWSClient;
    }

    @Override
    public ArrayList<AlRegSensorDTO> getSensors(String boardId) {
        String uri = "http://" + host + ":" + port + "/boards/{boardId}/sensors";
        Map<String, String> params = new HashMap<String, String>();
        params.put("boardId", boardId);
        AlRegSensorDTO[] sensors = springWSClient.getForObject(uri, AlRegSensorDTO[].class, params);
        return new ArrayList<>(Arrays.asList(sensors));
    }

    @Override
    public AlRegSensorDTO getSensor(String boardId, String sensorId) {
        String uri = "http://" + host + ":" + port + "/boards/{boardId}/sensors/{sensorId}";
        Map<String, String> params = new HashMap<String, String>();
        params.put("boardId", boardId);
        params.put("sensorId", sensorId);
        AlRegSensorDTO sensor = springWSClient.getForObject(uri, AlRegSensorDTO.class, params);
        return sensor;
    }

    @Override
    public Float getSensorValue(String boardId, String sensorId) {
        String uri = "http://" + host + ":" + port + "/boards/{boardId}/sensors/{sensorId}";
        Map<String, String> params = new HashMap<String, String>();
        params.put("boardId", boardId);
        params.put("sensorId", sensorId);
        AlRegSensorDTO sensor = springWSClient.getForObject(uri, AlRegSensorDTO.class, params);
        return sensor.getValue();
    }
}
