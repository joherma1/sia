package org.sysreg.sia.webservices;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.sysreg.sia.webservices.dto.BoardDTO;
import org.sysreg.sia.webservices.dto.SensorDTO;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by joseant on 11/07/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/applicationContext.xml")
public class BoardClientTest {

    @Autowired
    BoardClient boardClient;

    @Before
    public void setUp() {
        boardClient.setUrl("localhost");
        boardClient.setPort(3000);
    }

    @Test
    public void testConnection() {
        boardClient.testConnection();
    }

    @Test
    public void testGetBoards() {
        ArrayList<BoardDTO> boards = boardClient.getBoards();
        assertNotNull(boards);
        assertTrue(boards.size() > 0);
        BoardDTO board = boardClient.getBoard(boards.get(0).get_id());
        assertEquals(board, boards.get(0));
    }

    @Test
    public void testGetSensors(){
        ArrayList<BoardDTO> boards = boardClient.getBoards();
        assertNotNull(boards);
        assertTrue(boards.size() > 0);
        ArrayList<SensorDTO> sensors = boardClient.getSensors(boards.get(0).get_id());
        assertNotNull(sensors);
        for(SensorDTO sensor: sensors){
            assertNotEquals(sensor.get_id(),"");
        }
    }

    @Test
    public void testGetSensor(){
        ArrayList<BoardDTO> boards = boardClient.getBoards();
        assertNotNull(boards);
        assertTrue(boards.size() > 0);
        ArrayList<SensorDTO> sensors = boardClient.getSensors(boards.get(0).get_id());
        assertNotNull(sensors);
        for(SensorDTO sensor: sensors){
            assertEquals(sensor, boardClient.getSensor(boards.get(0).get_id(),sensor.get_id()));
        }
    }

    @Test
    public void testGetSensorValue(){
        ArrayList<BoardDTO> boards = boardClient.getBoards();
        assertNotNull(boards);
        assertTrue(boards.size() > 0);
        ArrayList<SensorDTO> sensors = boardClient.getSensors(boards.get(0).get_id());
        assertNotNull(sensors);
        for(SensorDTO sensor: sensors){
            assertEquals(sensor, boardClient.getSensor(boards.get(0).get_id(),sensor.get_id()));
            Float value = boardClient.getSensorValue(boards.get(0).get_id(),sensor.get_id());
            assertEquals(value, sensor.getValue());
        }
    }
}
