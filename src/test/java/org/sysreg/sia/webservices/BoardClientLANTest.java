package org.sysreg.sia.webservices;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by joseant on 11/07/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/applicationContext.xml")
public class BoardClientLANTest {

    @Autowired
    BoardClient boardClient;

    @Before
    public void setUp() {
        boardClient.setUrl("127.0.0.1");
        boardClient.setPort(3000);
    }

    @Test
    public void testConnection(){
        boardClient.testConnection();
    }

    @Test
    public void testGetBoard(){
        BoardDTO board = boardClient.getBoard("55a0e45766cfdc4b023f932d");
        assertNotNull(board);
    }

    @Test
    public void testGetBoards(){
        ArrayList<BoardDTO> boards = boardClient.getBoards();
        assertNotNull(boards);
        assertTrue(boards.size()>0);
    }
}
