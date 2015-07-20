package org.sysreg.sia.webservices.dto;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by joseant on 11/07/15.
 */
public class BoardWS {
    private static final Logger log = LogManager.getLogger(BoardWS.class.getName());

    RestTemplate springWSClient;

    private String url;

    private int port;

    public BoardWS() {
    }

    public BoardWS(String url, int port) {
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

}
