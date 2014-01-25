package org.sysreg.sia.model.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.sysreg.sia.model.Field;
import org.sysreg.sia.model.dao.FieldDAO;

@Repository
public class FieldDAOImpl implements FieldDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void persist(Field field) {
		entityManager.persist(field);
	}

}
