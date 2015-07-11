package org.sysreg.sia.model.dao.jpa;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    static final Logger logger = LogManager.getLogger(FieldDAO.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Field> findByUser(User user) {
        Query q = entityManager.createQuery("from Field where user = ?1", Field.class);
        q.setParameter(1, user);
        logger.debug("findByUser " +  q.unwrap(org.hibernate.Query.class).getQueryString());
        long startTime = System.nanoTime();
        List<Field> results = q.getResultList();
        logger.debug("Fields count: " + results.size());
        logger.debug("Profiling: " + (System.nanoTime() - startTime)/1000000 + "ms");
        return results;
    }

    @Override
    @Transactional
    public void persist(Field field) {
        entityManager.persist(field);
    }

}
