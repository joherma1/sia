package org.sysreg.sia.daos.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.sysreg.sia.model.Parcel;
import org.sysreg.sia.daos.ParcelDAO;

@Repository
public class DefaultParcelDAO implements ParcelDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void persist(Parcel parcel) {
		entityManager.persist(parcel);
	}

}
