package org.sysreg.sia.daos.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.sysreg.sia.model.Board;
import org.sysreg.sia.daos.BoardDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by joseant on 11/07/15.
 */
@Repository
public class DefaultBoardDAO implements BoardDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void persist(Board board) {
        entityManager.persist(board);
    }

    @Override
    @Transactional
    public Board findById(int id) {
        return entityManager.find(Board.class, id);
    }
}
