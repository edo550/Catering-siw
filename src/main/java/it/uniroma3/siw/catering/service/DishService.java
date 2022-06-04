package it.uniroma3.siw.catering.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.catering.model.Dish;
import it.uniroma3.siw.catering.repository.DishRepository;

@Service
public class DishService {

	@Autowired
	private DishRepository dishRepository;

	@Transactional
	public Dish inserisci(Dish dish) {
		return dishRepository.save(dish);
	}

	public Dish search(String name) {
		return dishRepository.findByName(name);
	}

	public boolean hasDuplicate(Dish dish) {
		return dishRepository.existsByName(dish.getName());
	}

	public Dish findById(Long id) {
		var p = dishRepository.findById(id);
		if (p.isPresent())
			return p.get();
		return null;
	}

	public List<Dish> findByIds(List<Long> ids) {
		var i = dishRepository.findAllById(ids);
		List<Dish> dishList = new ArrayList<>();
		for (Dish dish : i)
			dishList.add(dish);
		return dishList;
	}

	@Transactional
	public void save(Dish dish) {
		dishRepository.save(dish);
	}

	@Transactional

	public void deleteDishById(Long id) {
		dishRepository.deleteById(id);
	}

	public List<Dish> findAll() {
		List<Dish> l = new ArrayList<>();
		for (Dish i : dishRepository.findAll())
			l.add(i);
		return l;
	}
}