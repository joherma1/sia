package org.sysreg.sia.model.dao.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.sysreg.sia.model.actuator.Actuator;
import org.sysreg.sia.model.dao.ActuatorDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ActuatorDAOImpl implements ActuatorDAO {

	@PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Actuator findById(String id) {
        return entityManager.find(Actuator.class, id);
    }

	@Override
	@Transactional
	public void persist(Actuator actuator) {
        entityManager.persist(actuator);
	}

}
