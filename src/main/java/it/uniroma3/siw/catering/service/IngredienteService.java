package it.uniroma3.siw.catering.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.catering.model.Ingrediente;
import it.uniroma3.siw.catering.repository.IngredienteRepository;

@Service
public class IngredienteService {
	
	@Autowired
	private IngredienteRepository igrepo;
	
	@Transactional
	public void save(Ingrediente i) {
		this.igrepo.save(i);
	}
	
	@Transactional
	public void delete(Ingrediente i) {
		this.igrepo.delete(i);
	}
	
	@Transactional
	public void update(Ingrediente i, Ingrediente newIngrediente) {
		i.setNome(newIngrediente.getNome());
		i.setDescrizione(newIngrediente.getDescrizione());
		i.setOrigine(newIngrediente.getOrigine());
		this.igrepo.save(i);
	}

	public Optional<Ingrediente> findById(Long idIngrediente) {
		return this.igrepo.findById(idIngrediente);
	}
}
