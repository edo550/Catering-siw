package it.uniroma3.siw.catering.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.catering.model.Utente;

public interface UtenteRepository extends CrudRepository<Utente, Long>{

	public boolean existsByNomeAndCognome(String nome, String cognome);
}