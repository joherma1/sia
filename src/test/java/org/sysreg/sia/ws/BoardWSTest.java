package org.sysreg.sia.ws;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.sysreg.sia.ws.client.dto.AlRegBoardDTO;
import org.sysreg.sia.ws.client.dto.AlRegSensorDTO;
import org.sysreg.sia.ws.client.impl.AlRegBoardWSClient;

import java.util.List;

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
    AlRegBoardWSClient boardWSClient;

    @Value("${test.siarest.host}")
    String siarestHost;

    @Value("${test.siarest.port}")
    String siarestPort;

    @Before
    public void setUp() {
        boardWSClient.setHost(siarestHost);
        boardWSClient.setPort(Integer.parseInt(siarestPort));
    }

    @Test
    public void testConnection() {
        boardWSClient.testConnection();
    }

    @Test
    public void testGetBoards() {
        List<AlRegBoardDTO> boards = boardWSClient.getBoards();
        assertNotNull(boards);
        assertTrue(boards.size() > 0);
        AlRegBoardDTO board = boardWSClient.getBoard(boards.get(0).getId());
        assertEquals(board, boards.get(0));
    }

    @Test
    public void testGetSensors() {
        List<AlRegBoardDTO> boards = boardWSClient.getBoards();
        assertNotNull(boards);
        assertTrue(boards.size() > 0);
        List<AlRegSensorDTO> sensors = boardWSClient.getSensors(boards.get(0).getId());
        assertNotNull(sensors);
        for (AlRegSensorDTO sensor : sensors) {
            assertNotEquals(sensor.get_id(), "");
        }
    }

    @Test
    public void testGetSensor() {
        List<AlRegBoardDTO> boards = boardWSClient.getBoards();
        assertNotNull(boards);
        assertTrue(boards.size() > 0);
        List<AlRegSensorDTO> sensors = boardWSClient.getSensors(boards.get(0).getId());
        assertNotNull(sensors);
        for (AlRegSensorDTO sensor : sensors) {
            assertEquals(sensor, boardWSClient.getSensor(boards.get(0).getId(), sensor.getCode()));
        }
    }

    //@Test
    public void testGetSensorValue() {
        List<AlRegBoardDTO> boards = boardWSClient.getBoards();
        assertNotNull(boards);
        assertTrue(boards.size() > 0);
        List<AlRegSensorDTO> sensors = boardWSClient.getSensors(boards.get(0).getId());
        assertNotNull(sensors);
        for (AlRegSensorDTO sensor : sensors) {
            // Get value without having in account the timestamp
            Float value = boardWSClient.getSensorValue(boards.get(0).getId(), sensor.getCode());
            assertNotNull(value);
            //Get the value again but from the database, must be the same (<60s)
            assertEquals(sensor, boardWSClient.getSensor(boards.get(0).getId(), sensor.getCode()));
        }
    }
}
