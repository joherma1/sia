package org.sysreg.sia.facades;

import org.sysreg.sia.dtos.EnclosureDTO;
import org.sysreg.sia.dtos.FieldDTO;
import org.sysreg.sia.dtos.ServerInfoDTO;

import java.util.List;

/**
 * Created by joseant on 24/08/15.
 */
public interface FieldFacade {

    List<FieldDTO> getFields(String user);

    EnclosureDTO getEnclosure(String user, String id);

    ServerInfoDTO getServerInfoForBoard(String user, String boardId);
}
