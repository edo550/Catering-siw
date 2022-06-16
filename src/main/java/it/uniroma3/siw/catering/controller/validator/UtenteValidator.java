package it.uniroma3.siw.catering.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.catering.model.Utente;
import it.uniroma3.siw.catering.service.UtenteService;

@Component
public class UtenteValidator implements Validator {

	@Autowired
	private UtenteService uts;
	
	@Override
	public void validate(Object target, Errors errors) {
		if (this.uts.alreadyExists((Utente)target)) {
			errors.reject("utente.duplicato");
		}
	}
	@Override
	public boolean supports(Class<?> clazz) {
		return Utente.class.equals(clazz);
	}
}
