package org.sysreg.sia.ws.client;

import org.sysreg.sia.ws.client.dto.AlRegBoardDTO;

import java.util.ArrayList;

/**
 * Created by joseant on 24/08/15.
 */
public interface BoardWSClient {

    int getPort();

    void setPort(int port);

    String getHost();

    void setHost(String host);

    ArrayList<AlRegBoardDTO> getBoards();

    AlRegBoardDTO getBoard(String id);
}
