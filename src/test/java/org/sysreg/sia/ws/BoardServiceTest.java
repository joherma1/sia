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
import org.sysreg.sia.model.Board;
import org.sysreg.sia.model.sensor.Sensor;
import org.sysreg.sia.ws.service.BoardService;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by joseant on 20/07/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/applicationContext.xml")
public class BoardServiceTest {

    @Autowired
    MockHttpServletRequest request;

    @Autowired
    BoardService boardService;

    @Value("${test.siarest.host}")
    String siarestHost;

    @Value("${test.siarest.port}")
    String siarestPort;

    @Before
    public void setUp() {
        boardService.setHost(siarestHost);
        boardService.setPort(Integer.parseInt(siarestPort));
    }

    @Test
    public void testGetBoards() {
        List<Board> boards = boardService.getBoards();
        assertNotNull(boards);
        assertTrue(boards.size() > 0);
    }

    @Test
    public void testGetSensors() {
        List<Board> boards = boardService.getBoards();
        if (boards.size() > 0) {
            List<Sensor> sensors = boardService.getSensorsForBoard(Integer.toString(boards.get(0).getId()));
            for (Sensor sensorOrig : boards.get(0).getSensors()) {
                assertTrue(sensors.contains(sensorOrig));
            }
        }
    }

    /**
     * Test the difference bewteen
     * getSensorForId --> Get the sensor from the database
     * getSensorValue --> Get the sensor from the arduino (unless iS too recent [ < CHECK_INTERVAL])
     */
    //@Test
    public void testGetValueSensor() {
        List<Board> boards = boardService.getBoards();
        if (boards.size() > 0 && boards.get(0).getSensors().size() > 0) {
            Sensor sensor = boards.get(0).getSensors().iterator().next();
            Float value = boardService.getSensorValueForId(Integer.toString(sensor.getBoard().getId()), sensor.getCode());
            Date queryAgain = new Date();
            Sensor sensorUpdated = boardService.getSensorForId(Integer.toString(sensor.getBoard().getId()), sensor.getCode());

            // Test 1:
            // if(timestamp is recent) same value (not call the board)
            // if(timestamp is old) database updated [NORMAL CASE]
            if ((new Date().getTime() - sensor.getTimestamp().getTime()) / 1000 < 60) {
                //using the quickest conversion function
                assertEquals(sensor.getValue(), value.doubleValue(), 1e-15);
            } else {
                assertNotEquals(sensor.getTimestamp(), sensorUpdated.getTimestamp());
            }

            // Test 2:
            // Value recent --> not call Arduino
            value = boardService.getSensorValueForId(Integer.toHexString(boards.get(0).getId()), sensor.getCode());
            assertTrue((queryAgain.getTime() - sensorUpdated.getTimestamp().getTime()) / 1000 < 60);
            assertEquals(sensorUpdated.getValue(), value.doubleValue(), 1e-10);
        }
    }
}
