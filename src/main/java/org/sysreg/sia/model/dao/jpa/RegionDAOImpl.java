package org.sysreg.sia.model.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.sysreg.sia.model.Region;
import org.sysreg.sia.model.dao.RegionDAO;

@Repository
public class RegionDAOImpl implements RegionDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void persist(Region region) {
		entityManager.persist(region);
	}

}
