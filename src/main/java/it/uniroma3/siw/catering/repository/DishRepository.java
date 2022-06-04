package it.uniroma3.siw.catering.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.catering.model.Dish;
import it.uniroma3.siw.catering.model.Ingredient;

@Repository
public interface DishRepository extends CrudRepository<Dish, Long> {
	Dish findByName(String name);

	List<Dish> findByIngredientsContaining(Ingredient i);

	boolean existsByName(String name);
}