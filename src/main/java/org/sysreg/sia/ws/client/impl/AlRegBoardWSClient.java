package org.sysreg.sia.ws.client.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
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

    private String username;

    private String password = "";

    private HttpHeaders headers = new HttpHeaders();
    ;

    public AlRegBoardWSClient() {
    }

    public AlRegBoardWSClient(String host, int port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        setAuthorization(this.username, this.password);
    }

    private HttpHeaders setAuthorization(String username, String password) {
        //Set up the authentication header
        String plainCredentials = username + ":" + password;
        byte[] plainCredentialsBytes = plainCredentials.getBytes();
        byte[] plainCredentialsBytes64 = Base64.encode(plainCredentialsBytes);
        String base64Credentials = new String(plainCredentialsBytes64);

        headers.remove("Authorization");
        headers.add("Authorization", "Basic " + base64Credentials);
        return headers;
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

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
        setAuthorization(this.username, this.password);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
        setAuthorization(this.username, this.password);
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
            HttpEntity<String> request = new HttpEntity<String>(headers);
            ResponseEntity<AlRegBoardDTO[]> response =
                    springWSClient.exchange(uri, HttpMethod.GET, request, AlRegBoardDTO[].class);
            //In both the request and response, the type parameters (AlRegBoardDTO[]) represents the body
            AlRegBoardDTO[] boards = response.getBody();

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
            HttpEntity<String> request = new HttpEntity<String>(headers);
            ResponseEntity<AlRegBoardDTO> response =
                    springWSClient.exchange(uri, HttpMethod.GET, request, AlRegBoardDTO.class, params);
            AlRegBoardDTO board = response.getBody();
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
            HttpEntity<String> request = new HttpEntity<String>(headers);
            ResponseEntity<AlRegSensorDTO[]> response =
                    springWSClient.exchange(uri, HttpMethod.GET, request, AlRegSensorDTO[].class, params);
            AlRegSensorDTO[] sensors = response.getBody();
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
            HttpEntity<String> request = new HttpEntity<String>(headers);
            ResponseEntity<AlRegSensorDTO> response =
                    springWSClient.exchange(uri, HttpMethod.GET, request, AlRegSensorDTO.class, params);
            AlRegSensorDTO sensor = response.getBody();
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
            HttpEntity<String> request = new HttpEntity<String>(headers);
            ResponseEntity<AlRegSensorDTO> response =
                    springWSClient.exchange(uri, HttpMethod.GET, request, AlRegSensorDTO.class, params);
            AlRegSensorDTO sensor = response.getBody();
            return sensor.getValue();
        } catch (RestClientException e) {
            log.error("Error connecting with the board", e);
            return null;
        }
    }

}
