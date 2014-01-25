package org.sysreg.sia.model.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.sysreg.sia.model.Enclosure;
import org.sysreg.sia.model.dao.EnclosureDAO;

@Repository
public class EnclosureDAOImpl implements EnclosureDAO {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public void persist(Enclosure enclosure) {
		entityManager.persist(enclosure);
	}

}
