package it.uniroma3.siw.catering.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.catering.model.Chef;

@Repository
public interface ChefRepository extends CrudRepository<Chef, Long> {
	Chef findByFirstNameAndLastName(String firstName, String lastName);

	List<Chef> findByFirstName(String firstName);

	List<Chef> findByLastName(String lastName);

	boolean existsByFirstNameAndLastName(String firstName, String lastName);

	List<Chef> findByFirstNameOrLastName(String firstName, String lastName);
}