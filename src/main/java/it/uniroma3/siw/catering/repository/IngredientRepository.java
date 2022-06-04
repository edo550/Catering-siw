package it.uniroma3.siw.catering.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.catering.model.Ingredient;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

	List<Ingredient> findByNameOrOrigin(String name, String origin);

	List<Ingredient> findByName(String name);

	List<Ingredient> findByOrigin(String origin);

	boolean existsByNameAndOrigin(String name, String origin);

}
