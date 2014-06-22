package org.sysreg.sia.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

/**
 * Created by jose on 22/06/14.
 */
@Controller
public class DashboardController {
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard(ModelMap models, Principal principal) {
        return "dashboard";
    }
}
