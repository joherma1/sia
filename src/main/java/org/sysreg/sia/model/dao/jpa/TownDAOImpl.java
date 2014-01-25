package org.sysreg.sia.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.sysreg.sia.model.Town;
import org.sysreg.sia.model.dao.TownDAO;

@Repository
public class TownDAOImpl implements TownDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void persist(Town town) {
		entityManager.persist(town);
	}

	@Override
	@Transactional
	public Town findById(int id) {
		return entityManager.find(Town.class, id);
	}

	@Override
	@Transactional
	public List<Town> findAll() {
		return entityManager.createQuery("from Town", Town.class).getResultList();
	}

	@Override
	@Transactional
	public Town findByName(String name) {
		Query query = entityManager.createQuery("from Town where name = ?1", Town.class);
		query.setParameter(1, name);
		@SuppressWarnings("unchecked")
		List<Town> result = query.getResultList();
		if (result.size() > 0)
			return result.get(0);
		else
			return null;
	}
}
