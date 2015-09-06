package org.sysreg.sia.services;

import org.sysreg.sia.model.Enclosure;
import org.sysreg.sia.model.Field;
import org.sysreg.sia.model.Server;

import java.util.List;

/**
 * Created by joseant on 24/08/15.
 */
public interface FieldService {

    List<Field> getFieldsForUser(String user);

    Enclosure getEnclosureById(String user, String id);

    Server getServerByBoard(String user, String boardId);

}
