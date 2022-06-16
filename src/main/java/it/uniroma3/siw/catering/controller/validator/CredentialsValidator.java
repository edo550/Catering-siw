package it.uniroma3.siw.catering.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.catering.model.Credentials;
import it.uniroma3.siw.catering.service.CredentialsService;

@Component
public class CredentialsValidator implements Validator {

	@Autowired
	private CredentialsService creds;
	
	@Override
	public void validate(Object target, Errors errors) {
		if (this.creds.alreadyExists((Credentials)target)) {
			errors.reject("credenziali.duplicato");
		}
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Credentials.class.equals(clazz);
	}
}
