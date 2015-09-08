package org.sysreg.sia.daos;

import java.util.List;

import org.sysreg.sia.model.User;

public interface UserDAO {
	void persist(User user);

	void update(User user);

	void persistOrUpdate(User user);

	User findById(int id);

	List<User> findAll();
	
	User findByUsername(String username);

}
