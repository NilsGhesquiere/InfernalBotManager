package net.nilsghesquiere.web.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<ValidEmail,String>{

	private Pattern pattern;
	private Matcher matcher;
	private static final String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$"; 
	
	@Override
	public void initialize(ValidEmail constraintAnnotation) {
	}
	@Override
	public boolean isValid(String email, ConstraintValidatorContext context){
		return (validateEmail(email));
	} 
	private boolean validateEmail(String email) {
		pattern = Pattern.compile(EMAIL_PATTERN,Pattern.CASE_INSENSITIVE);
		matcher = pattern.matcher(email);
		return matcher.matches();
	}
}

