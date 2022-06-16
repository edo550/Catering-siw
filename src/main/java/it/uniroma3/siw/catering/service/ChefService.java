package it.uniroma3.siw.catering.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.repository.ChefRepository;

@Service
public class ChefService {

	@Autowired
	private ChefRepository chrepo;

	@Transactional
	public Chef save(Chef chef) {
		return chrepo.save(chef);
	}

	@Transactional
	public void delete(Long id) {
		this.chrepo.deleteById(id);
	}

	public boolean alreadyExists(Chef target) {
		return this.chrepo.existsByNomeAndCognomeAndNazionalita(target.getNome(), target.getCognome(),
				target.getNazionalita());
	}

	public Optional<Chef> findById(Long id) {
		return this.chrepo.findById(id);
	}

	public List<Chef> findAllChef() {
		List<Chef> tuttiChef = new ArrayList<Chef>();

		for (Chef c : chrepo.findAll()) {
			tuttiChef.add(c);
		}

		return tuttiChef;
	}

	public void update(Chef chef, Chef newChef) {
		chef.setNome(newChef.getNome());
		chef.setCognome(newChef.getCognome());
		chef.setNazionalita(newChef.getNazionalita());
		this.chrepo.save(chef);
	}
}