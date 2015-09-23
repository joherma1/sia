package org.sysreg.sia.daos.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.sysreg.sia.daos.VarietyDAO;
import org.sysreg.sia.model.Variety;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by joseant on 08/09/15.
 */
@Repository
public class DefaultVarietyDAO implements VarietyDAO {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional
    public void persist(Variety variety) {
        entityManager.persist(variety);
    }

    @Override
    public Variety findById(int id) {
        return entityManager.find(Variety.class, id);
    }

    @Override
    public Variety findByName(String name) {
        Query q = entityManager.createQuery("from Variety where upper(name) like upper(?1)", Variety.class);
        q.setParameter(1, name);
        List<Variety> results = q.getResultList();
        if (results.size() > 0)
            return results.get(0);
        else
            return null;
    }
}
