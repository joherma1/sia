package org.sysreg.sia.webservices.facade;

import org.sysreg.sia.model.Board;

import java.util.List;

/**
 * Created by joseant on 11/07/15.
 */
public interface RaspberryFacade {

    List<Board> getAllBoards();

    Board getBoard(String id);

    String getHost();

    void setHost(String host);

    int getPort();

    void setPort(int port);

}
