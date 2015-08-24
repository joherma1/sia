package org.sysreg.sia.daos;

import org.sysreg.sia.model.Enclosure;

public interface EnclosureDAO {
	void persist(Enclosure enclosure);
    Enclosure findById(String id);
}
