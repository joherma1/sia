package org.sysreg.sia.ws.service;

import org.sysreg.sia.model.Board;
import org.sysreg.sia.model.sensor.Sensor;

import java.util.List;

/**
 * Created by joseant on 20/07/15.
 */
public interface BoardService {

    String getHost();

    void setHost(String host);

    int getPort();

    void setPort(int port);
    
    String getUsername();
    
    void setUsername(String username);
    
    String getPassword();
    
    void setPassword(String password);

    List<Board> getBoards();

    Board getBoardForId(String id);

    List<Sensor> getSensorsForBoard(String boardId);

    Sensor getSensorForId(String boardId, String sensorId);

    /**
     * Access to the actual value for the sensor indicate
     * @param boardId
     * @param sensorId
     * @return Value read from the board, null otherwise
     */
    Float getSensorValueForId(String boardId, String sensorId);

    //TODO
    //getSensorLastValueForId - Without connecting with the server
}
