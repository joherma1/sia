package org.sysreg.sia.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;
import org.sysreg.sia.model.Enclosure;
import org.sysreg.sia.model.User;
import org.sysreg.sia.model.dao.EnclosureDAO;

import java.security.Principal;

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

    @Before
    public void setUp() {
        //Inject the mocks to the Controller
        dashboardController = new DashboardController(enclosureDAO);
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
