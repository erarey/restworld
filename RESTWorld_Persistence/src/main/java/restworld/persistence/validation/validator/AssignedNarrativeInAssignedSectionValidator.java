package restworld.persistence.validation.validator;

import javax.validation.ConstraintValidator;

import org.springframework.stereotype.Component;

import restworld.persistence.validation.annotation.AssignedNarrativeInAssignedSection;

@Component
public interface AssignedNarrativeInAssignedSectionValidator extends ConstraintValidator<AssignedNarrativeInAssignedSection, Object> {

}
