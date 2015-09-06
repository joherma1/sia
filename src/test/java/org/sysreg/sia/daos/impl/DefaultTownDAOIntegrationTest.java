package org.sysreg.sia.daos.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.sysreg.sia.daos.TownDAO;
import org.sysreg.sia.model.Town;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/applicationContext.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class DefaultTownDAOIntegrationTest {
	@Autowired
	private TownDAO TownDAO;

	@Test
	public void testSelect() {
		Town town = TownDAO.findByName("Alcàsser");
		assertEquals(46015, town.getId());
		assertEquals("L'Horta Sud", town.getRegion().getName());
		assertEquals("València", town.getRegion().getProvince().getName());
		
		assertEquals(12002, TownDAO.findById(12002).getId());
		
		assertEquals(542, TownDAO.findAll().size());
		
	}
}
