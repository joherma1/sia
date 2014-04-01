package org.sysreg.sia.model.actuator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.sysreg.sia.model.dao.ActuatorDAO;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by jose on 08/02/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/applicationContext.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class ActuatorTest {
    @Autowired
    private ActuatorDAO actuatorDao;

    @Test
    public void testCreation(){
        ArrayList<Actuator> actuators = new ArrayList<Actuator>();

        //Actuator
        actuators.add(new Actuator("1"));

        //Basic Actuator
        actuators.add(new BasicActuator("2"));

        for(Actuator i: actuators)
            System.out.println(i.toString());
    }

    @Test
    public void testInsertSelect(){
        ArrayList<Actuator> actuators = new ArrayList<Actuator>();
        Actuator actuator;

        //Actuator
        actuator = new Actuator("1");
        actuator.setDescription("Test actuator #1");
        actuator.setEnabled(true);
        actuators.add(actuator);

        //Basic Actuator
        actuator = new BasicActuator("2");
        actuator.setDescription("Test actuator #2");
        actuator.setEnabled(false);
        actuators.add(actuator);

        for(Actuator i: actuators)
            actuatorDao.persist(i);

        //SELECT
        actuator = actuatorDao.findById("1");
        assertEquals(actuator, actuators.get(0));

        actuator = actuatorDao.findById("2");
        assertEquals(actuator, actuators.get(1));
    }
}
