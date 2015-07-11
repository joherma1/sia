package org.sysreg.sia.model.dao;

import org.sysreg.sia.model.Board;

/**
 * Created by joseant on 11/07/15.
 */
public interface BoardDAO {

    void persist(Board board);

    Board findById(int id);
}
