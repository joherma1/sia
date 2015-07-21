package org.sysreg.sia.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.ui.ModelMap;
import org.sysreg.sia.model.Board;
import org.sysreg.sia.model.Enclosure;
import org.sysreg.sia.model.User;
import org.sysreg.sia.model.dao.EnclosureDAO;
import org.sysreg.sia.webservices.facade.RaspberryFacade;
import org.sysreg.sia.webservices.facade.impl.RaspberryFacadeImpl;
import org.sysreg.sia.webservices.model.BoardService;

import java.security.Principal;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

/**
 * Created by jose on 27/09/14.
 */
@RunWith(MockitoJUnitRunner.class)
public class DashboardControllerTest {

    private DashboardController dashboardController;

    //Services mocked
    @Mock
    private EnclosureDAO enclosureDAO;

    @Mock
    private ObjectFactory<RaspberryFacade> raspberryFacadeObjectFactory;

    @Mock
    private BoardService boardService;

    @Mock
    private RaspberryFacadeImpl raspberryFacade;

    @Before
    public void setUp() {
        //Inject the mocks to the Controller
        dashboardController = new DashboardController(enclosureDAO, raspberryFacadeObjectFactory);
    }

    @Test
    public void showEnclosureInfo() {
        //Create the test data
        Principal testUser = new Principal() {
            @Override
            public String getName() {
                return "Test User";
            }
        };

        User expectedUser = new User();
        expectedUser.setName(testUser.getName());

        Enclosure expectedEnclosure = new Enclosure();
        expectedEnclosure.setArea(23.F);
        expectedEnclosure.setSlope(1F);


        //Mock the services behaviour with mockito
        when(enclosureDAO.findById("expectedId")).thenReturn(expectedEnclosure);
        when(boardService.getBoards()).thenReturn(new ArrayList<Board>());
        when(raspberryFacade.getHost()).thenReturn("localhost");
        when(raspberryFacade.getPort()).thenReturn(3000);
        when(raspberryFacadeObjectFactory.getObject()).thenReturn(raspberryFacade);

        //Call the controller
        ModelMap model = new ModelMap();
        String viewName = dashboardController.dashboard("expectedId", model, testUser);

        //Check the controller results
        assertEquals("dashboard", viewName);
        assertSame(expectedEnclosure, model.get("enclosure"));

        //Verify that the services was called at least one time
        verify(enclosureDAO, atLeastOnce()).findById("expectedId");
    }
}
