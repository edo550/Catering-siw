package it.uniroma3.siw.catering.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.catering.model.Credentials;
import it.uniroma3.siw.catering.repository.CredentialsRepository;

@Service
public class CredentialsService {
	
	@Autowired
	private CredentialsRepository credrepo;
	
	@Autowired
	private PasswordEncoder pwenco;
	
	@Transactional
	public Credentials save(Credentials c) {
		c.setRole(Credentials.DEFAULT_ROLE);
		c.setPassword(this.pwenco.encode(c.getPassword()));
		return credrepo.save(c);
	}
	
    public Credentials getCredentials(Long id) {
        Optional<Credentials> result = this.credrepo.findById(id);
        return result.orElse(null);
    }

    public Credentials getCredentials(String username) {
        Optional<Credentials> result = this.credrepo.findByUsername(username);
        return result.orElse(null);
    }

	public boolean alreadyExists(Credentials target) {
		return credrepo.existsByUsername(target.getUsername());
	}
}