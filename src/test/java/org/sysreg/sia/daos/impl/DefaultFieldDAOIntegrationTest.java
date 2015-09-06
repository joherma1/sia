package org.sysreg.sia.daos.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.sysreg.sia.daos.FieldDAO;
import org.sysreg.sia.daos.TownDAO;
import org.sysreg.sia.daos.UseDAO;
import org.sysreg.sia.daos.UserDAO;
import org.sysreg.sia.model.*;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

/**
 * Created by jose on 27/09/14.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/applicationContext.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class DefaultFieldDAOIntegrationTest {
    @Autowired
    TownDAO townDAO;
    @Autowired
    UseDAO useDAO;
    @Autowired
    FieldDAO fieldDAO;
    @Autowired
    UserDAO userDAO;

    @Test
    public void testFindByUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setName("test user");
        user.setPassword("testpassword");

        Field field = new Field();
        field.setName("Field #1");
        //Parcel
        Parcel p1 = new Parcel();
        p1.setParcel(1);
        p1.setPolygon(1);
        p1.setAggregate(1);
        p1.setZone(1);
        p1.setTown(townDAO.findByName("Picassent"));
        p1.setArea(25F);
        Coordinates cP1 = new Coordinates();
        cP1.setX(36D);
        cP1.setY(45D);
        cP1.setDatum("DATUM1");
        cP1.setSpindle(29);
        p1.setCoordinates(cP1);
        //Enclosure
        //#1
        Enclosure e1 = new Enclosure();
        e1.setEnclosure(1);
        e1.setCoordinates(cP1);
        e1.setArea(20F);
        e1.setIrrigationCoef(100);
        e1.setSlope(0F);
        e1.setUse(useDAO.findById("CI"));
        //#2
        Enclosure e2 = new Enclosure();
        e2.setEnclosure(2);
        e2.setCoordinates(new Coordinates(37D, 45.5D, "DATUM1", 29));
        e2.setArea(5F);
        e2.setIrrigationCoef(100);
        e2.setSlope(0F);
        e2.setUse(useDAO.findById("CI"));
        //Set relations
        e1.setParcel(p1);
        e2.setParcel(p1);
        p1.setField(field);
        field.setUser(user);

        fieldDAO.persist(field);
        userDAO.persist(user);

        List<Field> result = fieldDAO.findByUser(user);
        assertNotNull(result);
        assertSame(field, result.get(0));
    }
}
