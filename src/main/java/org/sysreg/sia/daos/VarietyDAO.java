package org.sysreg.sia.daos;

import org.sysreg.sia.model.Variety;

/**
 * Created by joseant on 08/09/15.
 */
public interface VarietyDAO {
    void persist(Variety variety);

    Variety findById(int id);

    Variety findByName(String name);
}
