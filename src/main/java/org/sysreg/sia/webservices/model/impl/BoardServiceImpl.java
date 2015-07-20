package org.sysreg.sia.webservices.model.impl;

import org.sysreg.sia.model.Board;
import org.sysreg.sia.webservices.dto.BoardDTO;
import org.sysreg.sia.webservices.dto.BoardWS;
import org.sysreg.sia.webservices.model.BoardService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joseant on 20/07/15.
 */
public class BoardServiceImpl implements BoardService {

    String host;

    int port;

    BoardWS boardWS;

    public BoardServiceImpl() {
    }

    public BoardServiceImpl(String host, int port) {
        this.port = port;
        this.host = host;
        this.boardWS = new BoardWS(host, port);
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
        this.boardWS.setUrl(this.host);
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
        this.boardWS.setPort(this.port);
    }

    public BoardWS getBoardWS() {
        return boardWS;
    }

    public void setBoardWS(BoardWS boardWS) {
        this.boardWS = boardWS;
    }

    @Override
    public List<Board> getBoards() {
        //Converter BoardDTO into Board
        ArrayList<BoardDTO> boards =  boardWS.getBoards();
        List<Board> result = new ArrayList<>();
        for (BoardDTO board : boards){
            //TODO
            // Board model has port != board DTO two ids
            Board aux = new Board(Integer.parseInt(board.getId()), board.get_id(),board.getDescription());
            result.add(aux);
        }
        return result;
    }

    @Override
    public Board getBoard(String id) {
        return null;
    }
}
