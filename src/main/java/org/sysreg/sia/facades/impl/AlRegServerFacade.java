package org.sysreg.sia.facades.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sysreg.sia.dtos.ActuatorDTO;
import org.sysreg.sia.dtos.BoardDTO;
import org.sysreg.sia.dtos.SensorDTO;
import org.sysreg.sia.facades.ServerFacade;
import org.sysreg.sia.model.Board;
import org.sysreg.sia.model.actuator.Actuator;
import org.sysreg.sia.model.sensor.Sensor;
import org.sysreg.sia.ws.service.BoardService;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joseant on 24/08/15.
 */
public class AlRegServerFacade implements ServerFacade {
    private static final Logger log = LogManager.getLogger(AlRegServerFacade.class.getName());

    private BoardService boardService;

    public AlRegServerFacade(BoardService boardService) {
        this.boardService = boardService;
    }

    @Override
    public void initConnection(String host, int port) {
        boardService.setHost(host);
        boardService.setPort(port);
    }

    @Override
    public String getHost() {
        return boardService.getHost();
    }

    @Override
    public int getPort() {
        return boardService.getPort();
    }

    @Override
    public boolean testConnection() {
        return false;
    }

    @Override
    public List<BoardDTO> getBoards() {
        //Boards received from the server and transformed to the model
        List<Board> boards = boardService.getBoards();
        List<BoardDTO> boardsDTO = new ArrayList<>();
        //Convert to SIA DTO
        for (Board board : boards) {
            BoardDTO boardDTO = new BoardDTO();
            boardDTO.setId(Integer.toString(board.getId()));
            boardDTO.setDescription(board.getDescription());
            //Sensors
            List<SensorDTO> sensorsDTO = new ArrayList<>();
            for (Sensor sensor : board.getSensors()) {
                SensorDTO sensorDTO = new SensorDTO();
                sensorDTO.setId(sensor.getId());
                sensorDTO.setDescription(sensor.getCode() + " " + sensor.getDescription());
                sensorDTO.setValue(formatDouble(sensor.getValue()));
                sensorDTO.setUnits(sensor.getUnits() != null ? sensor.getUnits().toString() : null);
                sensorsDTO.add(sensorDTO);
            }
            boardDTO.setSensors(sensorsDTO);
            //Actuators
            List<ActuatorDTO> actuatorsDTO = new ArrayList<>();
            for (Actuator actuator : board.getActuators()) {
                ActuatorDTO actuatorDTO = new ActuatorDTO();
                actuatorDTO.setId(actuator.getId());
                actuatorDTO.setDescription(actuator.getDescription());
                actuatorDTO.setValue(Boolean.toString(actuator.isEnabled()));
                actuatorsDTO.add(actuatorDTO);
            }
            boardDTO.setActuators(actuatorsDTO);
            boardsDTO.add(boardDTO);
        }
        return boardsDTO;
    }

    @Override
    public BoardDTO getBoard(String boardId) {
        if (boardId == null || boardId.length() < 1)
            throw new IllegalArgumentException("The board " + boardId + " does not exist");

        Board board = boardService.getBoardForId(boardId);
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(Integer.toString(board.getId()));
        boardDTO.setDescription(board.getDescription());
        List<SensorDTO> sensorsDTO = new ArrayList<>();
        for (Sensor sensor : board.getSensors()) {
            SensorDTO sensorDTO = new SensorDTO();
            sensorDTO.setId(sensor.getId());
            sensorDTO.setDescription(sensor.getCode() + " " + sensor.getDescription());
            sensorDTO.setValue(formatDouble(sensor.getValue()));
            sensorDTO.setUnits(sensor.getUnits().toString());
            sensorsDTO.add(sensorDTO);
        }
        List<ActuatorDTO> actuatorsDTO = new ArrayList<>();
        for (Actuator actuator : board.getActuators()) {
            ActuatorDTO actuatorDTO = new ActuatorDTO();
            actuatorDTO.setId(actuator.getId());
            actuatorDTO.setDescription(actuator.getDescription());
            actuatorDTO.setValue(Boolean.toString(actuator.isEnabled()));
            actuatorsDTO.add(actuatorDTO);
        }
        return boardDTO;
    }

    @Override
    public SensorDTO getSensorValue(String boardId, String sensorId) {
        SensorDTO sensorDTO = new SensorDTO();
        sensorDTO.setId(sensorId);
        Float value = boardService.getSensorValueForId(boardId, sensorId);
        sensorDTO.setValue(formatFloat(value));
        return sensorDTO;
    }

    private String formatFloat(Float value) {
        String res = "";
        try {
            DecimalFormat df = new DecimalFormat(".##");
            res = df.format(value);
        } catch (IllegalArgumentException e) {
            log.error("Error parsing the value", e);
        }
        return res;
    }

    private String formatDouble(Double value) {
        String res = "";
        try {
            DecimalFormat df = new DecimalFormat(".##");
            res = df.format(value);
        } catch (IllegalArgumentException e) {
            log.error("Error parsing the value", e);
        }
        return res;
    }
}
