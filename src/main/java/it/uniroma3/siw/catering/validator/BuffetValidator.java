package it.uniroma3.siw.catering.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.service.BuffetService;

//https://docs.oracle.com/javaee/7/api/javax/validation/constraints/package-summary.html
@Component
public class BuffetValidator implements Validator {

	@Autowired
	private BuffetService buffetService;

	@Override
	public void validate(Object target, Errors errors) {
		if (this.buffetService.hasDuplicate((Buffet) target)) {
			errors.reject("buffet.duplicate", "duplicate buffet");
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Buffet.class.equals(clazz);
	}
}