package org.sysreg.sia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.sysreg.sia.daos.FieldDAO;
import org.sysreg.sia.daos.UserDAO;
import org.sysreg.sia.facades.FieldFacade;

import java.security.Principal;

@Controller
public class HomeController {
    @Autowired
    private FieldFacade fieldFacade;

    @RequestMapping(value={"/", "/home"}, method = RequestMethod.GET)
    public String showHomepage(ModelMap models, Principal principal) {
        //User has lazy initialization
        models.put("fields",fieldFacade.getFields(principal.getName()));
        return "home";
    }

}
