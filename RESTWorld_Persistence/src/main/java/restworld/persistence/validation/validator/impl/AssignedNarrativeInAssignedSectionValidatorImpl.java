package restworld.persistence.validation.validator.impl;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintValidatorContext;

import restworld.persistence.entity.Narrative;
import restworld.persistence.entity.Section;
import restworld.persistence.validation.annotation.AssignedNarrative;
import restworld.persistence.validation.annotation.AssignedNarrativeInAssignedSection;
import restworld.persistence.validation.annotation.AssignedSection;
import restworld.persistence.validation.validator.AssignedNarrativeInAssignedSectionValidator;

public class AssignedNarrativeInAssignedSectionValidatorImpl implements AssignedNarrativeInAssignedSectionValidator {

	@Override
	public void initialize(AssignedNarrativeInAssignedSection annotation) {

	}

	@Override
	public boolean isValid(Object target, ConstraintValidatorContext context) {

		try {

			Set<Long> validSectionsForNarratives = new HashSet<Long>();
			Set<Long> validSectionIds = new HashSet<Long>();

			for (Field f : target.getClass().getDeclaredFields()) {
				if (f.isAnnotationPresent(AssignedNarrative.class)) {
					f.setAccessible(true);

					//TODO null check!
					
					if (f.getType().isInstance(Narrative.class)) {

						validSectionsForNarratives.addAll(Narrative.class.cast(f.get(target)).getSections().stream()
								.map(section -> section.getId()).collect(Collectors.toSet()));

					} else if (f.getType().isInstance(Collection.class)) {

						Collection<Narrative> narratives = Collection.class.<Narrative>cast(f.get(target));
						for (Narrative narrative : narratives) {

							validSectionsForNarratives.addAll(narrative.getSections().stream()
									.map(section -> section.getId()).collect(Collectors.toSet()));

						}

					}
					
					//no narratives found, therefore section is irrelevant
					if(validSectionsForNarratives.size() == 0)
						return true;
					
					f.setAccessible(false);

				} else if (f.isAnnotationPresent(AssignedSection.class)) {
					f.setAccessible(true);

					if (f.getType().isInstance(Section.class)) {
						validSectionIds.add(Section.class.cast(f.get(target)).getId());
					} else if (f.getType().isInstance(Collection.class)) {
						Collection<Section> sections = Collection.class.<Section>cast(f.get(target));
						for (Section section : sections) {
							validSectionIds.add(section.getId());
						}
					}

					f.setAccessible(false);
				}
			}

			validSectionIds.retainAll(validSectionsForNarratives);
			return validSectionIds.size() > 0;

		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

}
