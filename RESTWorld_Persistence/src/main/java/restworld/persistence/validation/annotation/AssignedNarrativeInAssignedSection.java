package restworld.persistence.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import restworld.persistence.validation.validator.AssignedNarrativeInAssignedSectionValidator;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = { AssignedNarrativeInAssignedSectionValidator.class })
public @interface AssignedNarrativeInAssignedSection {
	
	String message() default "{restworld.persistence.validation.annotation.AssignedNarrativeInAssignedSection.message}";
	
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}
