package org.sysreg.sia.daos;

import java.util.List;

import org.sysreg.sia.model.Town;

public interface TownDAO {
	void persist(Town town);

	Town findById(int id);
	
	List<Town> findAll();

	Town findByName(String name);
}
