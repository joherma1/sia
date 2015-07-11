package org.sysreg.sia.webservices;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.sysreg.sia.model.Board;

import java.beans.ConstructorProperties;
import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by joseant on 11/07/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/applicationContext.xml")
public class BoardClientTest {

    @Autowired
    BoardClient client;

    //@Test
    public void testConnection(){
        client.testConnection("http://localhost:3000/boards/55a0e45766cfdc4b023f932d");
    }

    @Test
    public void testGetBoard(){
        BoardDTO board = client.getBoard("55a0e45766cfdc4b023f932d");
        assertNotNull(board);
    }

    @Test
    public void testGetBoards(){
        ArrayList<BoardDTO> boards = client.getBoards();
        assertNotNull(boards);
        assertTrue(boards.size()>0);
    }
}
