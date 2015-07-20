package org.sysreg.sia.webservices.dto;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by joseant on 20/07/15.
 */
public class SensorWS {
    private static final Logger log = LogManager.getLogger(SensorWS.class);

    RestTemplate springWSClient;

    private String url;

    private int port;


    public SensorWS() {
    }

    public SensorWS(String url, int port) {
        this.url = url;
        this.port = port;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public RestTemplate getSpringWSClient() {
        return springWSClient;
    }

    public void setSpringWSClient(RestTemplate springWSClient) {
        this.springWSClient = springWSClient;
    }

    public ArrayList<SensorDTO> getSensors(String boardId){
        String uri = "http://" + url + ":" + port + "/boards/{boardId}/sensors";
        Map<String, String> params = new HashMap<String, String>();
        params.put("boardId", boardId);
        SensorDTO[] sensors = springWSClient.getForObject(uri, SensorDTO[].class, params);
        return new ArrayList<>(Arrays.asList(sensors));
    }

    public SensorDTO getSensor(String boardId, String sensorId){
        String uri = "http://" + url + ":" + port + "/boards/{boardId}/sensors/{sensorId}";
        Map<String, String> params = new HashMap<String, String>();
        params.put("boardId", boardId);
        params.put("sensorId", sensorId);
        SensorDTO sensor = springWSClient.getForObject(uri, SensorDTO.class, params);
        return sensor;
    }

    public Float getSensorValue (String boardId, String sensorId){
        String uri = "http://" + url + ":" + port + "/boards/{boardId}/sensors/{sensorId}";
        Map<String, String> params = new HashMap<String, String>();
        params.put("boardId", boardId);
        params.put("sensorId", sensorId);
        SensorDTO sensor = springWSClient.getForObject(uri, SensorDTO.class, params);
        return sensor.getValue();
    }
}
