package org.sysreg.sia.webservices;

import org.sysreg.sia.webservices.dto.BoardDTO;
import org.sysreg.sia.webservices.dto.SensorDTO;

import java.util.List;

/**
 * Created by joseant on 11/07/15.
 */
public interface RaspberryFacade {

    List<BoardDTO> getAllBoards();

    BoardDTO getBoard(String id);

    List<SensorDTO> getAllSensors(String boardId);

    SensorDTO getSensor(String boardId, String sensorId);

}
