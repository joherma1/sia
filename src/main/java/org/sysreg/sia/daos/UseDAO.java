package org.sysreg.sia.daos;

import org.sysreg.sia.model.Use;

public interface UseDAO {
	void persist(Use use);

    Use findById(String id);
}
