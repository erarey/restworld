package restworld.persistence.validation.validator.impl;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import restworld.persistence.entity.Narrative;
import restworld.persistence.entity.Section;
import restworld.persistence.validation.annotation.AssignedNarrative;
import restworld.persistence.validation.annotation.AssignedNarrativeInAssignedSection;
import restworld.persistence.validation.annotation.AssignedSection;
import restworld.persistence.validation.validator.AssignedNarrativeInAssignedSectionValidator;

@Component
public class AssignedNarrativeInAssignedSectionValidatorImpl implements AssignedNarrativeInAssignedSectionValidator {

	@Override
	public void initialize(AssignedNarrativeInAssignedSection annotation) {

	}

	/**
	 * Type erasure ensures there is no way for this operation to be truly safe,
	 * so suppress warnings is acceptable until another solution is found
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean isValid(Object target, ConstraintValidatorContext context) {

		try {

			Set<Long> validSectionsForNarratives = new HashSet<Long>();
			Set<Long> validSectionIds = new HashSet<Long>();

			for (Field field : target.getClass().getDeclaredFields()) {
				
				if (field.isAnnotationPresent(AssignedNarrative.class)) {
					
					field.setAccessible(true);

					if (field.getType().isInstance(Narrative.class))
						validSectionsForNarratives = pullSectionIds(Narrative.class.cast(field.get(target)));
					else if (field.getType().isInstance(Collection.class))
						for (Narrative narrative : (Collection<Narrative>) field.get(target))
							validSectionsForNarratives.addAll(pullSectionIds(narrative));

					// no narratives found, therefore section is irrelevant
					if (validSectionsForNarratives.size() == 0)
						return true;
					field.setAccessible(false);
					
				} else if (field.isAnnotationPresent(AssignedSection.class)) {
					
					field.setAccessible(true);
					if (field.getType().isInstance(Section.class)) {
						Section annotatedSection = Section.class.cast(field.get(target));
						if(annotatedSection != null)
							validSectionIds.add(annotatedSection.getId());
					} else if (field.getType().isInstance(Collection.class)) {
						Collection<Section> sections = Collection.class.cast(field.get(target));
						for (Section section : sections)
							validSectionIds.add(section.getId());
					}
					field.setAccessible(false);
					
				}
			}
			// probably not possible to get null in both sets, going to play it safe though
			validSectionIds.remove(null);
			validSectionIds.retainAll(validSectionsForNarratives);
			return validSectionIds.size() > 0;
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return false;
	}

	private static Set<Long> pullSectionIds(Narrative narrative) {
		if (narrative != null) {
			Set<Section> narrativeSections = narrative.getSections();
			if (narrativeSections != null)
				return narrativeSections.stream().map(section -> section.getId()).collect(Collectors.toSet());
		}
		return Collections.emptySet();
	}

}
