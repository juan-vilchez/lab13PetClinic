package com.tecsup.petclinic.service;

import java.util.List;

import com.tecsup.petclinic.domain.Owner;
import com.tecsup.petclinic.exception.PetNotFoundException;


public interface OwnerService {
	Owner create(Owner owner);

	Owner update(Owner owner);

	void delete(Long id) throws PetNotFoundException;
	
	Owner findById(long id) throws PetNotFoundException;

	List<Owner> findByFirstName(String firstName);

	List<Owner> findByLastName(String lastName);

	List<Owner> findByAddress(String address);
	
	List<Owner> findByCity(String city);
	
	List<Owner> findByTelephone(String telephone);

	Iterable<Owner> findAll();
}