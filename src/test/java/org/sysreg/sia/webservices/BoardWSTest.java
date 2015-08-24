package org.sysreg.sia.webservices;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.sysreg.sia.webservices.dto.BoardDTO;
import org.sysreg.sia.webservices.client.impl.DefaultBoardWSClient;
import org.sysreg.sia.webservices.dto.SensorDTO;
import org.sysreg.sia.webservices.client.impl.DefaultSensorWSClient;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by joseant on 11/07/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/applicationContext.xml")
public class BoardWSTest {

    @Autowired
    MockHttpServletRequest request;

    @Autowired
    DefaultBoardWSClient boardWSClient;

    @Autowired
    DefaultSensorWSClient sensorWSClient;

    @Before
    public void setUp() {
        boardWSClient.setHost("localhost");
        boardWSClient.setPort(3000);
        sensorWSClient.setHost("localhost");
        sensorWSClient.setPort(3000);
    }

    @Test
    public void testConnection() {
        boardWSClient.testConnection();
    }

    @Test
    public void testGetBoards() {
        ArrayList<BoardDTO> boards = boardWSClient.getBoards();
        assertNotNull(boards);
        assertTrue(boards.size() > 0);
        BoardDTO board = boardWSClient.getBoard(boards.get(0).get_id());
        assertEquals(board, boards.get(0));
    }

    @Test
    public void testGetSensors() {
        ArrayList<BoardDTO> boards = boardWSClient.getBoards();
        assertNotNull(boards);
        assertTrue(boards.size() > 0);
        ArrayList<SensorDTO> sensors = sensorWSClient.getSensors(boards.get(0).get_id());
        assertNotNull(sensors);
        for (SensorDTO sensor : sensors) {
            assertNotEquals(sensor.get_id(), "");
        }
    }

    @Test
    public void testGetSensor() {
        ArrayList<BoardDTO> boards = boardWSClient.getBoards();
        assertNotNull(boards);
        assertTrue(boards.size() > 0);
        ArrayList<SensorDTO> sensors = sensorWSClient.getSensors(boards.get(0).get_id());
        assertNotNull(sensors);
        for (SensorDTO sensor : sensors) {
            assertEquals(sensor, sensorWSClient.getSensor(boards.get(0).get_id(), sensor.get_id()));
        }
    }

    @Test
    public void testGetSensorValue() {
        ArrayList<BoardDTO> boards = boardWSClient.getBoards();
        assertNotNull(boards);
        assertTrue(boards.size() > 0);
        ArrayList<SensorDTO> sensors = sensorWSClient.getSensors(boards.get(0).get_id());
        assertNotNull(sensors);
        for (SensorDTO sensor : sensors) {
            assertEquals(sensor, sensorWSClient.getSensor(boards.get(0).get_id(), sensor.get_id()));
            Float value = sensorWSClient.getSensorValue(boards.get(0).get_id(), sensor.get_id());
            assertEquals(value, sensor.getValue());
        }
    }
}
