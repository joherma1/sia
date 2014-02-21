package org.sysreg.sia.model.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.sysreg.sia.model.Field;
import org.sysreg.sia.model.Parcel;
import org.sysreg.sia.model.User;
import org.sysreg.sia.model.dao.FieldDAO;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FieldDAOImpl implements FieldDAO {

	@PersistenceContext
	private EntityManager entityManager;

    @Override
    @Transactional
    public List<Field> findByUser(User user) {
        Query q = entityManager.createQuery("from Field where user = ?1", Field.class);
        q.setParameter(1, user);
        List<Field> results = q.getResultList();
        return results;
    }

	@Override
	@Transactional
	public void persist(Field field) {
		entityManager.persist(field);
	}

}
