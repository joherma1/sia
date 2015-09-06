package org.sysreg.sia.daos.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.sysreg.sia.daos.ServerDAO;
import org.sysreg.sia.model.Server;
import org.sysreg.sia.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by joseant on 22/07/15.
 */
@Repository
public class DefaultServerDAO implements ServerDAO {

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

    @Override
    public Server findByUserAndBoard(User user, String boardId) {
        //TODO
        //search board by actual ID, not using the workaround description

        Query q = entityManager.createQuery("select server from Server as server " +
                "inner join server.boards as board " +
                "where server.enclosure.parcel.field.user.username = ?1 " +
                "and board.description = ?2"); //TODO
        q.setParameter(1, user.getUsername());
        q.setParameter(2, boardId);
        List<Server> results = q.getResultList();
        if (results.size() > 0)
            return results.get(0);
        else
            return null;
    }
}
