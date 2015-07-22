package org.sysreg.sia.model.dao.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.sysreg.sia.model.Server;
import org.sysreg.sia.model.dao.ServerDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by joseant on 22/07/15.
 */
@Repository
public class ServerDAOImpl implements ServerDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void persist(Server server) {
        entityManager.persist(server);
    }

    @Override
    @Transactional
    public Server findById(String id) {
        return entityManager.find(Server.class, id);
    }
}
