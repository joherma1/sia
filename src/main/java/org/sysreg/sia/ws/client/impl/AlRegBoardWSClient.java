package org.sysreg.sia.ws.client.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.client.RestTemplate;
import org.sysreg.sia.ws.client.BoardWSClient;
import org.sysreg.sia.ws.client.dto.AlRegBoardDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
    public ArrayList<AlRegBoardDTO> getBoards() {
        AlRegBoardDTO[] boards = springWSClient.getForObject("http://" + host + ":" + port + "/boards", AlRegBoardDTO[].class);
        return new ArrayList<AlRegBoardDTO>(Arrays.asList(boards));
    }

    @Override
    public AlRegBoardDTO getBoard(String id) {
        String uri = "http://" + host + ":" + port + "/boards/{id}";
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id);
        AlRegBoardDTO board = springWSClient.getForObject(uri, AlRegBoardDTO.class, params);
        return board;
    }

}
