package org.sysreg.sia.web.controller;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.sysreg.sia.dtos.EnclosureDTO;
import org.sysreg.sia.dtos.ServerDTO;
import org.sysreg.sia.dtos.ServerInfoDTO;
import org.sysreg.sia.facades.FieldFacade;
import org.sysreg.sia.facades.ServerFacade;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jose on 22/06/14.
 */
@Controller
public class DashboardController {

    private final FieldFacade fieldFacade;

    private final ObjectFactory<ServerFacade> serverFacadeObjectFactory;

    @Autowired
    public DashboardController(FieldFacade fieldFacade, ObjectFactory<ServerFacade> serverFacadeObjectFactory) {
        this.fieldFacade = fieldFacade;
        this.serverFacadeObjectFactory = serverFacadeObjectFactory;
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard(@RequestParam("enclosureId") String enclosureId, ModelMap model, Principal principal) {
        EnclosureDTO enclosureData = fieldFacade.getEnclosure(principal.getName(), enclosureId);
        List<ServerDTO> serversData = new ArrayList<>();
        //Get all the boards for the enclosure
        //For each server we connect with one client
        if (enclosureData.getServers() != null) {
            for (ServerInfoDTO server : enclosureData.getServers()) {
                ServerDTO serverData = new ServerDTO();
                serverData.setHost(server.getHost());
                serverData.setPort(server.getPort());
                serverData.setDescription(server.getDescription());
                // The boards are created as prototype, we could have n boards
                // Every time we invoque the request, a new Facade is created
                //TODO
                // Is this accomplished simply with scope="request"? is this necessary?
                ServerFacade serverFacade = serverFacadeObjectFactory.getObject();
                serverFacade.initConnection(serverData.getHost(), serverData.getPort());
                serverData.setBoards(serverFacade.getBoards());
                serversData.add(serverData);
            }
        }
        model.put("enclosure", enclosureData);
        model.put("servers", serversData);
        return "dashboard";
    }

}
