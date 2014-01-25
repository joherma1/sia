package org.sysreg.sia.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.sysreg.sia.model.dao.AuthorityDAO;
import org.sysreg.sia.model.dao.UserDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.assertNotNull;

/**
 * Created by jose on 22/01/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/applicationContext.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class AuthorityTest {
    @Autowired
    private AuthorityDAO AuthorityDAO;
    @Autowired
    private UserDAO UserDAO;
    @PersistenceContext
    private EntityManager entityManager;

    @Test(expected = javax.persistence.PersistenceException.class)
    public void testNameUnique(){
        String nameRole = "EXAMPLE_ROLE";

        Authority a1 = new Authority();
        a1.setName(nameRole);
        AuthorityDAO.persist(a1);
        assertNotNull(AuthorityDAO.findByName(nameRole));

        Authority a2 = new Authority();
        a2.setName(nameRole);
        AuthorityDAO.persist(a2);

        entityManager.flush();
    }

    @Test
    public void testSelect(){
        String defaultRole = ("ROLE_USER");
        String defaultUsername = ("sia");

        Authority defaultAuth = AuthorityDAO.findByName(defaultRole);
        assertNotNull(defaultAuth);

        User defaultUser = UserDAO.findByUsername(defaultUsername);
        assertNotNull(defaultUser);

        Authority authDefaultUser = AuthorityDAO.findByUser(defaultUser);
        assertNotNull(authDefaultUser);
    }
}
