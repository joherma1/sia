package org.sysreg.sia.ws.service.impl;

import org.sysreg.sia.model.Board;
import org.sysreg.sia.model.sensor.Sensor;
import org.sysreg.sia.ws.client.dto.AlRegBoardDTO;
import org.sysreg.sia.ws.client.dto.AlRegSensorDTO;
import org.sysreg.sia.ws.client.impl.AlRegBoardWSClient;
import org.sysreg.sia.ws.service.BoardService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by joseant on 20/07/15.
 */
public class DefaultBoardService implements BoardService {

    private AlRegBoardWSClient boardWSClient;

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

    public AlRegBoardWSClient getBoardWSClient() {
        return boardWSClient;
    }

    public void setBoardWSClient(AlRegBoardWSClient boardWSClient) {
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
        //Converter AlRegBoardDTO into Board + Get all the sensor + Get all the actuators
        ArrayList<AlRegBoardDTO> boards = boardWSClient.getBoards();
        List<Board> result = new ArrayList<>();
        for (AlRegBoardDTO board : boards) {
            //TODO
            // Board model has port != board DTO two ids
            Board aux = new Board(Integer.parseInt(board.getId()), board.get_id(), board.getDescription());
            //Get Sensors
            AlRegBoardDTO boardFull = boardWSClient.getBoard(board.get_id());
            Set<Sensor> sensors = new HashSet<>();
            for (AlRegSensorDTO alRegSensorDTO : boardFull.getSensors()) {
                Sensor sensor = new Sensor();
                sensor.setDescription(alRegSensorDTO.getDescription());
                sensor.setId(alRegSensorDTO.get_id());
                sensor.setValue(alRegSensorDTO.getValue() != null ? alRegSensorDTO.getValue() : 0D);
                sensor.setCode(alRegSensorDTO.getCode());
                sensor.setBoard(aux);
                sensors.add(sensor);
            }
            aux.setSensors(sensors);
            //Get Actuators
            //TODO
            result.add(aux);
        }
        return result;
    }

    @Override
    public Board getBoard(String id) {
        return null;
    }
}
