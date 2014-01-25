package org.sysreg.sia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sysreg.sia.model.dao.*;

@Controller
public class TownsController {

    @Autowired
    TownDAO TownDAO;

	@RequestMapping("/towns")
	public String towns(ModelMap models) {
        models.put("towns", TownDAO.findAll());
		return "towns";
	}
}
