package org.sysreg.sia.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * Created by jose on 21/09/14.
 */
@Controller
public class SensorsController {

    static final Logger logger = LogManager.getLogger(SensorsController.class.getName());

    @RequestMapping("/sensors")
    public ModelAndView helloAjaxTest() {
        return new ModelAndView("sensors", "message", "Ajax test code");
    }


    @RequestMapping(value = "/sensorsget", method = RequestMethod.GET)
    public @ResponseBody String getSensor(@RequestParam String id) {
        String result = id + "Other things";
        logger.debug("Debug Message " + id + " "  + new Date().toString());
        //To reply a raw string
        return  "\"" + result + "\"";
    }
}