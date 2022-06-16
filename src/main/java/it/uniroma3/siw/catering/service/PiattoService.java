package it.uniroma3.siw.catering.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.catering.model.Piatto;
import it.uniroma3.siw.catering.repository.PiattoRepository;

@Service
public class PiattoService {

	@Autowired
	private PiattoRepository ptrepo;
	
	@Transactional
	public void save(@Valid Piatto piatto) {
		this.ptrepo.save(piatto);
	}
	
	@Transactional
	public void delete(Piatto piatto) {
		this.ptrepo.delete(piatto);
	}
	
	@Transactional
	public void update(Piatto piatto, Piatto newPiatto) {
		piatto.setNome(newPiatto.getNome());
		piatto.setDescrizione(newPiatto.getDescrizione());
		this.ptrepo.save(piatto);
	}
	
	public Optional<Piatto> findById(Long id) {
		return this.ptrepo.findById(id);
	}

	public boolean alreadyExists(Piatto target) {
		return this.ptrepo.existsByNomeAndDescrizione(target.getNome(), target.getDescrizione());
	}
}