package it.uniroma3.siw.catering.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.catering.model.Ingredient;
import it.uniroma3.siw.catering.service.IngredientService;

//https://docs.oracle.com/javaee/7/api/javax/validation/constraints/package-summary.html
@Component
public class IngredientValidator implements Validator {

	@Autowired
	private IngredientService ingredientService;

	@Override
	public void validate(Object target, Errors errors) {
		if (this.ingredientService.hasDuplicate((Ingredient) target)) {
			errors.reject("ingredient.duplicate", "duplicate ingredient");
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Ingredient.class.equals(clazz);
	}
}