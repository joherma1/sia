package org.sysreg.sia.model.dao.jpa;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.sysreg.sia.model.Field;
import org.sysreg.sia.model.User;
import org.sysreg.sia.model.dao.FieldDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class FieldDAOImpl implements FieldDAO {

    final static Logger logger = Logger.getLogger(FieldDAO.class);

	@PersistenceContext
	private EntityManager entityManager;

    @Override
    @Transactional
    public List<Field> findByUser(User user) {
        Query q = entityManager.createQuery("from Field where user = ?1", Field.class);
        q.setParameter(1, user);
        logger.debug(q.unwrap(org.hibernate.Query.class).getQueryString());
        List<Field> results = q.getResultList();
        logger.debug("Fields #: " + results.size());
        return results;
    }

	@Override
	@Transactional
	public void persist(Field field) {
		entityManager.persist(field);
	}

}
