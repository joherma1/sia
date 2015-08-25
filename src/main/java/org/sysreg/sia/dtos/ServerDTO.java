package org.sysreg.sia.dtos;

import java.util.List;

/**
 * Created by joseant on 24/08/15.
 */
public class ServerDTO {
    private String host;

    private int port;

    private String description;

    private List<BoardDTO> boards;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<BoardDTO> getBoards() {
        return boards;
    }

    public void setBoards(List<BoardDTO> boards) {
        this.boards = boards;
    }
}
