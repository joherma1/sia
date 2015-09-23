package org.sysreg.sia.daos;

import org.sysreg.sia.model.Field;
import org.sysreg.sia.model.User;

import java.util.List;

public interface FieldDAO {

    List<Field> findByUser(User user);

	void persist(Field field);

    void update(Field field);

    void persistOrUpdate(Field field);

}
