package org.sysreg.sia.webservices.facade.impl;

import org.springframework.util.Assert;
import org.sysreg.sia.model.Board;
import org.sysreg.sia.webservices.facade.RaspberryFacade;
import org.sysreg.sia.webservices.BoardService;

import java.util.List;

/**
 * Created by joseant on 20/07/15.
 */
public class DefaultRaspberryFacade implements RaspberryFacade {

    private BoardService boardService;

    public BoardService getBoardService() {
        return boardService;
    }

    public void setBoardService(BoardService boardService) {
        this.boardService = boardService;
    }

    public String getHost() {
        return boardService.getHost();
    }

    public void setHost(String host) {
        boardService.setHost(host);
    }

    public int getPort() {
        return boardService.getPort();
    }

    public void setPort(int port) {
        boardService.setPort(port);
    }

    public DefaultRaspberryFacade(BoardService boardService) {
        this.boardService = boardService;
    }

    public void initConnection(String host, int port){
        this.setHost(host);
        this.setPort(port);
    }

    @Override
    //TODO
    //Define specific MVC DTO
    public List<Board> getBoards() {
        Assert.hasText(this.getHost(), "Host is mandatory");
        Assert.isTrue(this.getPort() > 0, "Port is mandatory");
        return boardService.getBoards();
    }

    @Override
    public Board getBoard(String id) {
        return boardService.getBoard(id);
    }
}
