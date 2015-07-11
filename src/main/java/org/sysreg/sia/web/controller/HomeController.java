package org.sysreg.sia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.sysreg.sia.model.dao.FieldDAO;
import org.sysreg.sia.model.dao.UserDAO;

import java.security.Principal;

@Controller
public class HomeController {
    @Autowired
    FieldDAO fieldDao;
    @Autowired
    UserDAO userDao;

    @RequestMapping(value={"/", "/home"}, method = RequestMethod.GET)
    public String showHomepage(ModelMap models, Principal principal) {
        //User has lazy initialization
        models.put("fields", fieldDao.findByUser(userDao.findByUsername(principal.getName())));
        models.put("user", userDao.findByUsername(principal.getName()));
        return "home";
    }

}
