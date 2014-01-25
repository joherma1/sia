package org.sysreg.sia.model.dao;

import org.sysreg.sia.model.Authority;
import org.sysreg.sia.model.User;

import java.util.List;

public interface AuthorityDAO {
	void persist(Authority authority);

    Authority findById(int id);

	List<Authority> findAll();

    Authority findByUser(User user);

    Authority findByName(String name);

}
