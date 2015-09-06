package org.sysreg.sia.daos.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.sysreg.sia.model.Region;
import org.sysreg.sia.daos.RegionDAO;

@Repository
public class DefaultRegionDAO implements RegionDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void persist(Region region) {
		entityManager.persist(region);
	}

}
