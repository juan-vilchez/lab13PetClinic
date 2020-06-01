package com.tecsup.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tecsup.petclinic.domain.Owner;
import com.tecsup.petclinic.domain.Pet;
import com.tecsup.petclinic.exception.PetNotFoundException;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class OwnerServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(OwnerServiceTest.class);

	@Autowired
	private OwnerService ownerService;

	/**
	 * 
	 */
	@Test
	public void testFindOwnerById() {

		long ID = 1;
		String NAME = "George";
		Owner owner = null;
		
		try {
			owner = ownerService.findById(ID);
		} catch (PetNotFoundException e) {
			fail(e.getMessage());
		}
		logger.info("" + owner);

		assertEquals(NAME, owner.getFirstName());

	}

	
	@Test
	public void testCreateOwner() {

		String FIRST_NAME = "Juan";
		String LAST_NAME = "Vilchez";
		String ADDRESS_NAME = "Ate-123-Mz4";
		String CITY_NAME = "Lima";
		String TELEPH = "987654321";

		Owner owner = new Owner(FIRST_NAME,LAST_NAME, ADDRESS_NAME,CITY_NAME,TELEPH);
		owner = ownerService.create(owner);
		logger.info("" + owner);

		assertThat(owner.getId()).isNotNull();
		assertEquals(FIRST_NAME, owner.getFirstName());
		assertEquals(LAST_NAME, owner.getLastName());
		assertEquals(ADDRESS_NAME, owner.getAddress());
		assertEquals(CITY_NAME, owner.getCity());
		assertEquals(TELEPH, owner.getTelephone());

	}

	@Test
	public void testUpdateOwner() {

		String FIRST_NAME = "Luis";
		String LAST_NAME = "Sanchez";
		String ADDRESS_NAME = "Lince-3-Mz1";
		String CITY_NAME = "Trujillo";
		String TELEPH = "988888888";
		long create_id = -1;

		String UP_FIRST_NAME = "Luis2";
		String UP_LAST_NAME = "Sanchez2";
		String UP_ADDRESS_NAME = "Lince-3-Mz22";
		String UP_CITY_NAME = "Trujillo2";
		String UP_TELEPH = "999999999";

		Owner owner = new Owner(FIRST_NAME,LAST_NAME, ADDRESS_NAME,CITY_NAME,TELEPH);

		// Create record
		logger.info(">" + owner);
		Owner readOwner = ownerService.create(owner);
		logger.info(">>" + readOwner);

		create_id = readOwner.getId();

		// Prepare data for update
		readOwner.setFirstName(UP_FIRST_NAME);
		readOwner.setLastName(UP_LAST_NAME);
		readOwner.setAddress(UP_ADDRESS_NAME);
		readOwner.setCity(UP_CITY_NAME);
		readOwner.setTelephone(UP_TELEPH);

		// Execute update
		Owner upgradeOwner = ownerService.update(readOwner);
		logger.info(">>>>" + upgradeOwner);

		assertThat(create_id).isNotNull();
		assertEquals(create_id, upgradeOwner.getId());
		assertEquals(UP_FIRST_NAME, upgradeOwner.getFirstName());
		assertEquals(UP_LAST_NAME, upgradeOwner.getLastName());
		assertEquals(UP_ADDRESS_NAME, upgradeOwner.getAddress());
		assertEquals(UP_CITY_NAME, upgradeOwner.getCity());
		assertEquals(UP_TELEPH, upgradeOwner.getTelephone());
	}
	
	@Test
	public void testDeleteOwner() {

		String FIRST_NAME = "Andrea";
		String LAST_NAME = "Perez";
		String ADDRESS_NAME = "Santa Anita-3-Mz6";
		String CITY_NAME = "Arequipa";
		String TELEPH = "963636363";

		Owner owner = new Owner(FIRST_NAME,LAST_NAME, ADDRESS_NAME,CITY_NAME,TELEPH);
		owner = ownerService.create(owner);
		logger.info("" + owner);
		
		try {
			ownerService.delete(owner.getId());
		} catch (PetNotFoundException e) {
			fail(e.getMessage());
		}
			
		try {
			ownerService.findById(owner.getId());
			assertTrue(false);
		} catch (PetNotFoundException e) {
			assertTrue(true);
		} 
				

	}
	
	
}
