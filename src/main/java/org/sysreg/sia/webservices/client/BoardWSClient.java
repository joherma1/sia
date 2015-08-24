package org.sysreg.sia.webservices.client;

import org.sysreg.sia.webservices.dto.BoardDTO;

import java.util.ArrayList;

/**
 * Created by joseant on 24/08/15.
 */
public interface BoardWSClient {

    int getPort();

    void setPort(int port);

    String getHost();

    void setHost(String host);

    ArrayList<BoardDTO> getBoards();

    BoardDTO getBoard(String id);
}
