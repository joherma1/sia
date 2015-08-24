package org.sysreg.sia.daos;

import org.sysreg.sia.model.actuator.Actuator;

public interface ActuatorDAO {

    Actuator findById(String id);

	void persist(Actuator actuator);

}
