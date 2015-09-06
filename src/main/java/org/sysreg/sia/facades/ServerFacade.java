package org.sysreg.sia.facades;

import org.sysreg.sia.dtos.BoardDTO;
import org.sysreg.sia.dtos.SensorDTO;

import java.util.List;

/**
 * Created by joseant on 24/08/15.
 */
public interface ServerFacade {

    void initConnection(String host, int port);

    String getHost();

    int getPort();

    boolean testConnection();

    List<BoardDTO> getBoards();

    BoardDTO getBoard(String boardId);

    SensorDTO getSensorValue(String boardId, String sensorId);

}
