package org.sysreg.sia.webservices;

import org.sysreg.sia.model.Board;

import java.util.List;

/**
 * Created by joseant on 20/07/15.
 */
public interface BoardService {

    List<Board> getBoards();

    Board getBoard(String id);


    String getHost();

    void setHost(String host);

    int getPort();

    void setPort(int port);

}
