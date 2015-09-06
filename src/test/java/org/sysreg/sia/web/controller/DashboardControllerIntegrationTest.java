package org.sysreg.sia.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.ui.ModelMap;
import org.sysreg.sia.dtos.EnclosureDTO;
import org.sysreg.sia.facades.FieldFacade;
import org.sysreg.sia.facades.ServerFacade;
import org.sysreg.sia.facades.impl.AlRegServerFacade;
import org.sysreg.sia.model.Board;
import org.sysreg.sia.model.User;
import org.sysreg.sia.ws.service.BoardService;

import java.security.Principal;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

/**
 * Created by jose on 27/09/14.
 */
@RunWith(MockitoJUnitRunner.class)
public class DashboardControllerIntegrationTest {

    private DashboardController dashboardController;

    //Services mocked
    @Mock
    private FieldFacade fieldFacade;

    @Mock
    private ObjectFactory<ServerFacade> serverFacadeObjectFactory;

    @Mock
    private BoardService boardService;

    @Mock
    private AlRegServerFacade serverFacade;

    @Before
    public void setUp() {
        //Inject the mocks to the Controller
        dashboardController = new DashboardController(fieldFacade, serverFacadeObjectFactory);
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

        EnclosureDTO expectedEnclosure = new EnclosureDTO();
        expectedEnclosure.setArea(23.F);
        expectedEnclosure.setSlope(1F);


        //Mock the services behaviour with mockito
        when(fieldFacade.getEnclosure(testUser.getName(), "expectedId")).thenReturn(expectedEnclosure);
        when(boardService.getBoards()).thenReturn(new ArrayList<Board>());
        when(serverFacade.getHost()).thenReturn("localhost");
        when(serverFacade.getPort()).thenReturn(3000);
        when(serverFacadeObjectFactory.getObject()).thenReturn(serverFacade);

        //Call the controller
        ModelMap model = new ModelMap();
        String viewName = dashboardController.dashboard("expectedId", model, testUser);

        //Check the controller results
        assertEquals("dashboard", viewName);
        assertSame(expectedEnclosure, model.get("enclosure"));

        //Verify that the services was called at least one time
        verify(fieldFacade, atLeastOnce()).getEnclosure(testUser.getName(), "expectedId");
    }
}
