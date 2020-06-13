package com.packt.cardatabase.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.packt.cardatabase.models.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long>{
	List<Owner> findByFirstNameAndLastName(String firstName, String lastName);
}
