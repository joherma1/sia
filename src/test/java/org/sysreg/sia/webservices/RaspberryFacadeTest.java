package org.sysreg.sia.webservices;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.sysreg.sia.model.Board;
import org.sysreg.sia.webservices.facade.RaspberryFacade;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by joseant on 20/07/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/applicationContext.xml")
public class RaspberryFacadeTest {

    @Autowired
    RaspberryFacade raspberryFacade;

    @Before
    public void setUp(){
        raspberryFacade.initConnection("localhost", 3000);
    }

    @Test
    public void testGetBoards(){
        List<Board> boards = raspberryFacade.getAllBoards();
        assertNotNull(boards);
        assertTrue(boards.size() > 0);
    }
}
