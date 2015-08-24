package org.sysreg.sia.webservices.client.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.client.RestTemplate;
import org.sysreg.sia.webservices.client.SensorWSClient;
import org.sysreg.sia.webservices.dto.SensorDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by joseant on 20/07/15.
 */
public class DefaultSensorWSClient implements SensorWSClient{
    private static final Logger log = LogManager.getLogger(DefaultSensorWSClient.class);

    RestTemplate springWSClient;

    private String host;

    private int port;


    public DefaultSensorWSClient() {
    }

    public DefaultSensorWSClient(String host, int port) {
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
    public ArrayList<SensorDTO> getSensors(String boardId){
        String uri = "http://" + host + ":" + port + "/boards/{boardId}/sensors";
        Map<String, String> params = new HashMap<String, String>();
        params.put("boardId", boardId);
        SensorDTO[] sensors = springWSClient.getForObject(uri, SensorDTO[].class, params);
        return new ArrayList<>(Arrays.asList(sensors));
    }

    @Override
    public SensorDTO getSensor(String boardId, String sensorId){
        String uri = "http://" + host + ":" + port + "/boards/{boardId}/sensors/{sensorId}";
        Map<String, String> params = new HashMap<String, String>();
        params.put("boardId", boardId);
        params.put("sensorId", sensorId);
        SensorDTO sensor = springWSClient.getForObject(uri, SensorDTO.class, params);
        return sensor;
    }

    @Override
    public Float getSensorValue (String boardId, String sensorId){
        String uri = "http://" + host + ":" + port + "/boards/{boardId}/sensors/{sensorId}";
        Map<String, String> params = new HashMap<String, String>();
        params.put("boardId", boardId);
        params.put("sensorId", sensorId);
        SensorDTO sensor = springWSClient.getForObject(uri, SensorDTO.class, params);
        return sensor.getValue();
    }
}
