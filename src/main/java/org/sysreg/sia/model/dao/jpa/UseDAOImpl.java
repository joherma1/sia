package org.sysreg.sia.model.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.sysreg.sia.model.Use;
import org.sysreg.sia.model.dao.UseDAO;

@Repository
public class UseDAOImpl implements UseDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void persist(Use use) {
		entityManager.persist(use);
	}

}
