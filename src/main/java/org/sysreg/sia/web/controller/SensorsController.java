package org.sysreg.sia.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.sysreg.sia.dtos.SensorDTO;
import org.sysreg.sia.dtos.ServerInfoDTO;
import org.sysreg.sia.facades.FieldFacade;
import org.sysreg.sia.facades.ServerFacade;

import java.security.Principal;
import java.text.DecimalFormat;

/**
 * Created by jose on 21/09/14.
 */
@Controller
public class SensorsController {

    static final Logger logger = LogManager.getLogger(SensorsController.class.getName());

    @Autowired
    private ObjectFactory<ServerFacade> serverFacadeObjectFactory;

    @Autowired
    private FieldFacade fieldFacade;

    public void setServerFacadeObjectFactory(ObjectFactory<ServerFacade> serverFacadeObjectFactory) {
        this.serverFacadeObjectFactory = serverFacadeObjectFactory;
    }

    public void setFieldFacade(FieldFacade fieldFacade) {
        this.fieldFacade = fieldFacade;
    }

    @RequestMapping("/sensors")
    public ModelAndView helloAjaxTest() {
        return new ModelAndView("sensors", "message", "Ajax test code");
    }


    @RequestMapping(value = "/sensorsget", method = RequestMethod.GET)
    public
    @ResponseBody
    String getSensor(@RequestParam String boardId, @RequestParam String sensorId, Principal principal) {
        ServerInfoDTO serverData = fieldFacade.getServerInfoForBoard(principal.getName(), boardId);

        ServerFacade serverFacade = serverFacadeObjectFactory.getObject();
        if (serverData != null) {
            //TODO
            serverFacade.initConnection(serverData.getHost(), serverData.getPort(),
                    serverData.getUsername(), serverData.getPassword());
            SensorDTO sensorDTO = serverFacade.getSensorValue(boardId, sensorId);
            logger.debug("getSensor(" + boardId + ", " + sensorId + ") received. New value: " + sensorDTO.getValue());
            return sensorDTO.getValue();
        } else {
            return "--";
        }

    }
}