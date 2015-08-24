package org.sysreg.sia.webservices.impl;

import org.sysreg.sia.model.Board;
import org.sysreg.sia.webservices.dto.BoardDTO;
import org.sysreg.sia.webservices.dto.BoardWS;
import org.sysreg.sia.webservices.BoardService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joseant on 20/07/15.
 */
public class DefaultBoardService implements BoardService {

    private BoardWS boardWS;

    public String getHost() {
        return boardWS.getHost();
    }

    public void setHost(String host) {
        boardWS.setHost(host);
    }

    public int getPort() {
        return boardWS.getPort();
    }

    public void setPort(int port) {
        this.boardWS.setPort(port);
    }

    public BoardWS getBoardWS() {
        return boardWS;
    }

    public void setBoardWS(BoardWS boardWS) {
        this.boardWS = boardWS;
    }

    public DefaultBoardService() {
    }

    public DefaultBoardService(String host, int port) {
        this.setHost(host);
        this.setPort(port);
    }

    @Override
    public List<Board> getBoards() {
        //Converter BoardDTO into Board
        ArrayList<BoardDTO> boards = boardWS.getBoards();
        List<Board> result = new ArrayList<>();
        for (BoardDTO board : boards) {
            //TODO
            // Board model has port != board DTO two ids
            Board aux = new Board(Integer.parseInt(board.getId()), board.get_id(), board.getDescription());
            result.add(aux);
        }
        return result;
    }

    @Override
    public Board getBoard(String id) {
        return null;
    }
}
