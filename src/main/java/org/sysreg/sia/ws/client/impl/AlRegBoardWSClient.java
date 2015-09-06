package org.sysreg.sia.ws.client.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.sysreg.sia.ws.client.BoardWSClient;
import org.sysreg.sia.ws.client.dto.AlRegBoardDTO;
import org.sysreg.sia.ws.client.dto.AlRegSensorDTO;

import java.util.*;

/**
 * Created by joseant on 11/07/15.
 */
public class AlRegBoardWSClient implements BoardWSClient {
    private static final Logger log = LogManager.getLogger(AlRegBoardWSClient.class.getName());

    private RestTemplate springWSClient;

    private String host;

    private int port;

    public AlRegBoardWSClient() {
    }

    public AlRegBoardWSClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public void setPort(int port) {
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

    public RestTemplate getSpringWSClient() {
        return springWSClient;
    }

    public void setSpringWSClient(RestTemplate springWSClient) {
        this.springWSClient = springWSClient;
    }

    public void testConnection() {
        springWSClient.getForObject("http://" + host + ":" + port, String.class);
    }

    @Override
    public List<AlRegBoardDTO> getBoards() {
        try {
            String uri = "http://" + host + ":" + port + "/boards";
            AlRegBoardDTO[] boards = springWSClient.getForObject(uri, AlRegBoardDTO[].class);
            return new ArrayList<>(Arrays.asList(boards));
        } catch (RestClientException e) {
            log.error("Error connecting with the board", e);
            return null;
        }
    }

    @Override
    public AlRegBoardDTO getBoard(String id) {
        try {
            String uri = "http://" + host + ":" + port + "/boards/{id}";
            Map<String, String> params = new HashMap<String, String>();
            params.put("id", id);
            AlRegBoardDTO board = springWSClient.getForObject(uri, AlRegBoardDTO.class, params);
            return board;
        } catch (RestClientException e) {
            log.error("Error connecting with the board", e);
            return null;
        }
    }

    @Override
    public List<AlRegSensorDTO> getSensors(String boardId) {
        try {
            String uri = "http://" + host + ":" + port + "/boards/{boardId}/sensors";
            Map<String, String> params = new HashMap<>();
            params.put("boardId", boardId);
            AlRegSensorDTO[] sensors = springWSClient.getForObject(uri, AlRegSensorDTO[].class, params);
            return new ArrayList<>(Arrays.asList(sensors));
        } catch (RestClientException e) {
            log.error("Error connecting with the board", e);
            return null;
        }
    }

    @Override
    public AlRegSensorDTO getSensor(String boardId, String sensorId) {
        try {
            String uri = "http://" + host + ":" + port + "/boards/{boardId}/sensors/{sensorId}";
            Map<String, String> params = new HashMap<>();
            params.put("boardId", boardId);
            params.put("sensorId", sensorId);
            AlRegSensorDTO sensor = springWSClient.getForObject(uri, AlRegSensorDTO.class, params);
            return sensor;
        } catch (RestClientException e) {
            log.error("Error connecting with the board", e);
            return null;
        }
    }

    @Override
    public Float getSensorValue(String boardId, String sensorId) {
        try {
            String uri = "http://" + host + ":" + port + "/boards/{boardId}/sensors/{sensorId}/value";
            Map<String, String> params = new HashMap<>();
            params.put("boardId", boardId);
            params.put("sensorId", sensorId);
            AlRegSensorDTO sensor = springWSClient.getForObject(uri, AlRegSensorDTO.class, params);
            return sensor.getValue();
        } catch (RestClientException e) {
            log.error("Error connecting with the board", e);
            return null;
        }
    }

}
