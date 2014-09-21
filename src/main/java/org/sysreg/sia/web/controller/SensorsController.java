package org.sysreg.sia.web.controller;

import com.fasterxml.jackson.annotation.JsonRawValue;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.sysreg.sia.model.Coordinates;
import sun.jvm.hotspot.jdi.IntegerTypeImpl;

import java.util.Date;
import java.util.Random;

/**
 * Created by jose on 21/09/14.
 */
@Controller
public class SensorsController {
    @RequestMapping("/sensors")
    public ModelAndView helloAjaxTest() {
        return new ModelAndView("sensors", "message", "Ajax test code");
    }


    @RequestMapping(value = "/sensorsget", method = RequestMethod.GET)
    public @ResponseBody String getSensor(@RequestParam String id) {
        Random rand = new Random();
        float r = rand.nextFloat() * 100;
        String result = id + "Other things";
        System.out.println("Debug Message " + id + " "  + new Date().toString());
        //To reply a raw string
        return  "\"" + result + "\"";
    }
}