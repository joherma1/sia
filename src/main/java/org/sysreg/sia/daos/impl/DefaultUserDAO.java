package org.sysreg.sia.daos.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.sysreg.sia.model.User;
import org.sysreg.sia.daos.UserDAO;

@Repository
public class DefaultUserDAO implements UserDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void persist(User user) {
		entityManager.persist(user);
	}

	@Override
	@Transactional
	public void update(User user){
		entityManager.merge(user);
	}

	@Override
	@Transactional
	public void persistOrUpdate(User user){
		if(user.getId() == 0) //New
			entityManager.persist(user);
		else
			entityManager.merge(user);
	}

	@Override
	@Transactional
	public User findById(int id) {
		return entityManager.find(User.class, id);
	}

	@Override
	@Transactional
	public List<User> findAll() {
		return entityManager.createQuery("from User",User.class).getResultList();
	}

	@Override
	@Transactional
	public User findByUsername(String username) {
		Query q = entityManager.createQuery("from User where username = ?1", User.class);
		q.setParameter(1, username);
		List<User> results = q.getResultList();
		if(results.size()>0)
			return results.get(0);
		else
			return null;
	}
	
	
	

}
