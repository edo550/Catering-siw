package it.uniroma3.siw.catering.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.repository.BuffetRepository;

@Service
public class BuffetService {

	@Autowired
	private BuffetRepository bfrepo;
	
	@Transactional
	public void save(Buffet b) {
		this.bfrepo.save(b);
	}

	@Transactional
	public void update(Buffet buffet, Buffet newBuffet) {
		buffet.setNome(newBuffet.getNome());
		buffet.setDescrizione(newBuffet.getDescrizione());
		this.bfrepo.save(buffet);
	}
	
	@Transactional
	public void delete(Buffet buffet) {
		this.bfrepo.delete(buffet);
	}
	
	public boolean alreadyExists(Buffet target) {
		return this.bfrepo.existsByNome(target.getNome());
	}

	public Optional<Buffet> findById(Long id) {
		return this.bfrepo.findById(id);
	}

	public List<Buffet> findAll() {
		return (List<Buffet>) this.bfrepo.findAll();
	}
}