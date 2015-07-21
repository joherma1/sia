package org.sysreg.sia.web.controller;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.sysreg.sia.model.Enclosure;
import org.sysreg.sia.model.dao.EnclosureDAO;
import org.sysreg.sia.webservices.facade.RaspberryFacade;
import org.sysreg.sia.webservices.facade.impl.RaspberryFacadeImpl;

import java.security.Principal;

/**
 * Created by jose on 22/06/14.
 */
@Controller
public class DashboardController{

    private final EnclosureDAO enclosureDAO;

    private final ObjectFactory<RaspberryFacade> raspberryFacadeObjectFactory;

    @Autowired
    public DashboardController(EnclosureDAO enclosureDAO, ObjectFactory<RaspberryFacade> raspberryFacadeObjectFactory){
        this.enclosureDAO = enclosureDAO;
        this.raspberryFacadeObjectFactory = raspberryFacadeObjectFactory;
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard(@RequestParam("enclosureId") String enclosureId, ModelMap model, Principal principal) {
        Enclosure enclosure = enclosureDAO.findById(enclosureId);
        // The boards are created as prototype, we could have n boards
        RaspberryFacade raspberryFacade = raspberryFacadeObjectFactory.getObject();
        raspberryFacade.initConnection("localhost", 3000);
        model.put("enclosure", enclosure);
        model.put("sensors", raspberryFacade.getAllBoards());
        return "dashboard";
    }

}
