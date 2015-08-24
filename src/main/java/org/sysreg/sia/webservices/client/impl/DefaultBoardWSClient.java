package org.sysreg.sia.webservices.client.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.client.RestTemplate;
import org.sysreg.sia.webservices.client.BoardWSClient;
import org.sysreg.sia.webservices.dto.BoardDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by joseant on 11/07/15.
 */
public class DefaultBoardWSClient implements BoardWSClient {
    private static final Logger log = LogManager.getLogger(DefaultBoardWSClient.class.getName());

    private RestTemplate springWSClient;

    private String host;

    private int port;

    public DefaultBoardWSClient() {
    }

    public DefaultBoardWSClient(String host, int port) {
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
    public ArrayList<BoardDTO> getBoards() {
        BoardDTO[] boards = springWSClient.getForObject("http://" + host + ":" + port + "/boards", BoardDTO[].class);
        return new ArrayList<BoardDTO>(Arrays.asList(boards));
    }

    @Override
    public BoardDTO getBoard(String id) {
        String uri = "http://" + host + ":" + port + "/boards/{id}";
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id);
        BoardDTO board = springWSClient.getForObject(uri, BoardDTO.class, params);
        return board;
    }

}
