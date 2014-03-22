package org.sysreg.sia.model.dao.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.sysreg.sia.model.dao.SensorDAO;
import org.sysreg.sia.model.sensor.Sensor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class SensorDAOImpl implements SensorDAO {

	@PersistenceContext
	private EntityManager entityManager;

    @Override
    @Transactional
    public Sensor findById(String id) {
        return entityManager.find(Sensor.class, id);
    }

	@Override
	@Transactional
	public void persist(Sensor sensor) {
		entityManager.persist(sensor);
	}

}
