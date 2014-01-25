package org.sysreg.sia.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login()
    {
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout()
    {
        return "logout";
    }

    @RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
    public String accessdenied()
    {
        return "accessdenied";
    }

}
