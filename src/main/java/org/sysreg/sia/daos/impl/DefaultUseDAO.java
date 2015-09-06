package org.sysreg.sia.daos.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.sysreg.sia.model.Use;
import org.sysreg.sia.daos.UseDAO;

@Repository
public class DefaultUseDAO implements UseDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void persist(Use use) {
		entityManager.persist(use);
	}

    @Override
    @Transactional
    public Use findById(String id) {
        return entityManager.find(Use.class, id);
    }

}
