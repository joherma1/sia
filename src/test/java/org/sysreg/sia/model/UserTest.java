package org.sysreg.sia.model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.sysreg.sia.model.dao.TownDAO;
import org.sysreg.sia.model.dao.UserDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/applicationContext.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class UserTest {
	@Autowired
	private UserDAO UserDAO;
	@Autowired
	private TownDAO TownDAO;

	@Test
	public void testInsertSelect(){
		User user = new User();
		user.setName("UsuarioT");
		user.setSurname("Apellido1T Apellido2T");
		user.setAddress("DireccionT CodigoPostalT LocalidadT ProvinciaT PaisT");
		user.setMobile("987654321T");
		user.setDni("12345678T");
		user.setUsername("usuarioT");
		user.setPassword("passwordT");
		user.setTown(TownDAO.findById(46015));
		UserDAO.persist(user);
		User user2 = new User();
		user2.setName("UsuarioT2");
		user2.setSurname("Apellido1T2 Apellido2T");
		user2.setAddress("DireccionT2 CodigoPostalT2 LocalidadT2 ProvinciaT2 PaisT2");
		user2.setMobile("987654321T2");
		user2.setDni("12345678T2");
		user2.setUsername("usuarioT2");
		user2.setPassword("passwordT2");
		UserDAO.persist(user2);
		
		User result = UserDAO.findByUsername("usuarioT");
		User result2 = UserDAO.findByUsername("usuarioT2");
		assertSame(user, result);
		assertEquals(46015, result.getTown().getId());
		assertEquals("València", result.getTown().getProvince().getName());
		assertNotSame(user, result2);
		assertNull(result2.getTown());
	}

}
