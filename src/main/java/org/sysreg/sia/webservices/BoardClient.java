package org.sysreg.sia.webservices;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.client.RestTemplate;
import org.sysreg.sia.webservices.dto.BoardDTO;
import org.sysreg.sia.webservices.dto.SensorDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by joseant on 11/07/15.
 */
public class BoardClient {
    private static final Logger log = LogManager.getLogger(BoardClient.class.getName());

    RestTemplate springWSClient;

    private String url;

    private int port;

    public BoardClient() {
    }

    public BoardClient(String url, int port) {
        this.url = url;
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public RestTemplate getSpringWSClient() {
        return springWSClient;
    }

    public void setSpringWSClient(RestTemplate springWSClient) {
        this.springWSClient = springWSClient;
    }

    public void testConnection() {
        springWSClient.getForObject("http://" + url + ":" + port, String.class);
    }

    public ArrayList<BoardDTO> getBoards() {
        BoardDTO[] boards = springWSClient.getForObject("http://" + url + ":" + port + "/boards", BoardDTO[].class);
        return new ArrayList<BoardDTO>(Arrays.asList(boards));
    }

    public BoardDTO getBoard(String id) {
        String uri = "http://" + url + ":" + port + "/boards/{id}";
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id);
        BoardDTO board = springWSClient.getForObject(uri, BoardDTO.class, params);
        return board;
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
