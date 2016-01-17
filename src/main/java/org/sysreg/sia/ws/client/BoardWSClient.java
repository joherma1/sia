package org.sysreg.sia.ws.client;

import org.sysreg.sia.ws.client.dto.AlRegBoardDTO;
import org.sysreg.sia.ws.client.dto.AlRegSensorDTO;

import java.util.List;

/**
 * Created by joseant on 24/08/15.
 */
public interface BoardWSClient {

    int getPort();

    void setPort(int port);

    String getHost();

    void setHost(String host);
    
    //TODO define authentication Object-> Type, user, password, ids....
    String getUsername();
    
    void setUsername(String username);
    
    String getPassword();
    
    void setPassword(String password);

    List<AlRegBoardDTO> getBoards();

    AlRegBoardDTO getBoard(String id);

    List<AlRegSensorDTO> getSensors(String boardId);

    AlRegSensorDTO getSensor(String boardId, String sensorId);

    Float getSensorValue(String boardId, String sensorId);
}
