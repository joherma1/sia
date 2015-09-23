package org.sysreg.sia.ws.service.impl;

import org.sysreg.sia.model.Board;
import org.sysreg.sia.model.sensor.Sensor;
import org.sysreg.sia.ws.client.dto.AlRegBoardDTO;
import org.sysreg.sia.ws.client.dto.AlRegSensorDTO;
import org.sysreg.sia.ws.client.impl.AlRegBoardWSClient;
import org.sysreg.sia.ws.service.BoardService;
import sun.misc.FloatingDecimal;

import java.util.*;

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
        List<AlRegBoardDTO> boardsDTO = boardWSClient.getBoards();
        List<Board> result = new ArrayList<>();
        for (AlRegBoardDTO boardDTO : boardsDTO) {
            Board aux = new Board(Integer.parseInt(boardDTO.getId()), boardDTO.getProtocol(), boardDTO.getUri(), boardDTO.getDescription());
            //Get Sensors
            AlRegBoardDTO boardFull = boardWSClient.getBoard(boardDTO.getId());
            Set<Sensor> sensors = new HashSet<>();
            for (AlRegSensorDTO alRegSensorDTO : boardFull.getSensors()) {
                Sensor sensor = new Sensor();
                //sensor ID (Postgres) not set, internal to the database
                sensor.setCode(alRegSensorDTO.getCode());
                sensor.setDescription(alRegSensorDTO.getDescription());
                // Use of FloatingDecimal to avoid deltas conversion (e.g. 25.69999999)
                sensor.setValue(alRegSensorDTO.getValue() != null ?
                        new FloatingDecimal(alRegSensorDTO.getValue()).doubleValue() : 0D);
                sensor.setTimestamp(alRegSensorDTO.getTimestamp() != null ? alRegSensorDTO.getTimestamp() : new Date(0));
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
    public Board getBoardForId(String id) {
        AlRegBoardDTO boardDTO = boardWSClient.getBoard(id);
        Board board = new Board(Integer.parseInt(boardDTO.getId()), boardDTO.getProtocol(), boardDTO.getUri(), boardDTO.getDescription());

        //Sensor converter
        Set<Sensor> sensors = new HashSet<>();
        for (AlRegSensorDTO alRegSensorDTO : boardDTO.getSensors()) {
            Sensor sensor = new Sensor();
            //sensor ID (Postgres) not set, internal to the database
            sensor.setCode(alRegSensorDTO.getCode());
            sensor.setDescription(alRegSensorDTO.getDescription());
            sensor.setValue(alRegSensorDTO.getValue() != null ?
                    new FloatingDecimal(alRegSensorDTO.getValue()).doubleValue() : 0D);
            sensor.setTimestamp(alRegSensorDTO.getTimestamp() != null ? alRegSensorDTO.getTimestamp() : new Date(0));
            sensor.setBoard(board);
            sensors.add(sensor);
        }
        board.setSensors(sensors);
        //Get Actuators
        //TODO
        return board;
    }

    @Override
    public List<Sensor> getSensorsForBoard(String boardId) {
        List<AlRegSensorDTO> sensorsDTO = boardWSClient.getSensors(boardId);

        //Sensor Converter
        List<Sensor> sensors = new ArrayList<>();
        for (AlRegSensorDTO alRegSensorDTO : sensorsDTO) {
            Sensor sensor = new Sensor();
            //sensor ID (Postgres) not set, internal to the database
            sensor.setCode(alRegSensorDTO.getCode());
            sensor.setDescription(alRegSensorDTO.getDescription());
            sensor.setValue(alRegSensorDTO.getValue() != null ?
                    new FloatingDecimal(alRegSensorDTO.getValue()).doubleValue() : 0D);
            sensor.setTimestamp(alRegSensorDTO.getTimestamp() != null ? alRegSensorDTO.getTimestamp() : new Date(0));
            //TODO
            //Eager board initialization: Set board attribute
            //sensor.setBoard(null);
            sensors.add(sensor);
        }
        return sensors;
    }

    @Override
    public Sensor getSensorForId(String boardId, String sensorId) {
        AlRegSensorDTO sensorDTO = boardWSClient.getSensor(boardId, sensorId);
        Sensor sensor = new Sensor();
        //sensor ID (Postgres) not set, internal to the database
        sensor.setCode(sensorDTO.getCode());
        sensor.setDescription(sensorDTO.getDescription());
        sensor.setValue(sensorDTO.getValue() != null ?
                new FloatingDecimal(sensorDTO.getValue()).doubleValue() : 0D);
        sensor.setTimestamp(sensorDTO.getTimestamp() != null ? sensorDTO.getTimestamp() : new Date(0));
        //TODO
        //Eager board initialization
        //sensor.setBoard(null);
        return sensor;
    }

    @Override
    public Float getSensorValueForId(String boardId, String sensorId) {
        // TODO
        // Service layer must manage extensions
        return boardWSClient.getSensorValue(boardId, sensorId);
    }
}
