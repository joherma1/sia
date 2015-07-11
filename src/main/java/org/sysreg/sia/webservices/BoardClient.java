package org.sysreg.sia.webservices;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by joseant on 11/07/15.
 */
public class BoardClient {
    private static final Logger log = LogManager.getLogger(BoardClient.class.getName());

    private String url;

    private int port;

    private RestTemplate restTemplate;

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

    public void testConnection(String uri) {
        restTemplate.getForObject(uri, String.class);
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ArrayList<BoardDTO> getBoards() {
        BoardDTO[] boards = restTemplate.getForObject("http://" + url + ":" + port + "/boards", BoardDTO[].class);
        log.info(boards);
        return new ArrayList<BoardDTO>(Arrays.asList(boards));
    }

    public BoardDTO getBoard(String id) {
        RestTemplate restTemplate = new RestTemplate();
        String uri = "http://localhost:3000/boards/{id}";
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "55a0e45766cfdc4b023f932d");

        BoardDTO board = restTemplate.getForObject(uri, BoardDTO.class, params);
        log.info(board);
        return board;
    }
}
