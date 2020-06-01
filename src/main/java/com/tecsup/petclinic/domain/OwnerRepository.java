package com.tecsup.petclinic.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Long> {

	// Fetch owner by first name
		List<Owner> findByFirstName(String firstName);

		// Fetch owner by last name
		List<Owner> findByLastName(String lastName);

		// Fetch owner by address
		List<Owner> findByAddress(String address);
		
		// Fetch owner by city
		List<Owner> findByCity(String city);
		
		// Fetch owner by telephone
		List<Owner> findByTelephone(String telephone);

}
