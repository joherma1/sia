package org.sysreg.sia.webservices.facade.impl;

import org.sysreg.sia.model.Board;
import org.sysreg.sia.webservices.facade.RaspberryFacade;
import org.sysreg.sia.webservices.model.BoardService;
import org.sysreg.sia.webservices.model.impl.BoardServiceImpl;

import java.util.List;

/**
 * Created by joseant on 20/07/15.
 */
public class RaspberryFacadeImpl implements RaspberryFacade {

    private String host;

    private int port;

    BoardService boardService;

    public BoardService getBoardService() {
        return boardService;
    }

    public void setBoardService(BoardService boardService) {
        this.boardService = boardService;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String url) {
        this.host = url;
        this.boardService.setHost(this.host);
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
        this.boardService.setPort(this.port);
    }

    public RaspberryFacadeImpl() {
    }

    public RaspberryFacadeImpl(BoardService boardService) {
        this.boardService = boardService;
        this.boardService.setHost(this.host);
        this.boardService.setPort(this.port);
    }

    public RaspberryFacadeImpl(String url, int host) {
        this.host = url;
        this.port = host;
        this.boardService = new BoardServiceImpl(url, host);
    }

//    @Bean
//    @Scope(value = "prototype")
//    public RaspberryFacade myPrototype(String url, int port) {
//        return new RaspberryFacadeImpl(url, port);
//    }

    @Override
    //TODO
    //Define specific MVC DTO
    public List<Board> getAllBoards() {
        return boardService.getBoards();
    }

    @Override
    public Board getBoard(String id) {
        return boardService.getBoard(id);
    }
}
