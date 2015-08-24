package org.sysreg.sia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.sysreg.sia.daos.FieldDAO;
import org.sysreg.sia.daos.UserDAO;

import java.security.Principal;

@Controller
public class FieldsController {

    private final FieldDAO fieldDao;
    private final UserDAO userDao;

    @Autowired
    public FieldsController(UserDAO userDao,FieldDAO fieldDao){
        this.userDao = userDao;
        this.fieldDao = fieldDao;
    }

    @RequestMapping(value="/fields", method = RequestMethod.GET)
    public String showFieldsPage(ModelMap models, Principal principal){
        //User has lazy initialization
        models.put("fields", fieldDao.findByUser(userDao.findByUsername(principal.getName())));
        return "fields";
    }
}
