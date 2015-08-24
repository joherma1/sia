package org.sysreg.sia.webservices.impl;

import org.sysreg.sia.model.Board;
import org.sysreg.sia.webservices.dto.BoardDTO;
import org.sysreg.sia.webservices.client.impl.DefaultBoardWSClient;
import org.sysreg.sia.webservices.BoardService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joseant on 20/07/15.
 */
public class DefaultBoardService implements BoardService {

    private DefaultBoardWSClient boardWSClient;

    public String getHost() {
        return boardWSClient.getHost();
    }

    public void setHost(String host) {
        boardWSClient.setHost(host);
    }

    public int getPort() {
        return boardWSClient.getPort();
    }

    public void setPort(int port) {
        this.boardWSClient.setPort(port);
    }

    public DefaultBoardWSClient getBoardWSClient() {
        return boardWSClient;
    }

    public void setBoardWSClient(DefaultBoardWSClient boardWSClient) {
        this.boardWSClient = boardWSClient;
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
        ArrayList<BoardDTO> boards = boardWSClient.getBoards();
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
