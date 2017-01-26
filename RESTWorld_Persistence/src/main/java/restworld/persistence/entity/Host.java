package restworld.persistence.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import restworld.persistence.entity.embeddable.FullName;
import restworld.persistence.entity.superclass.Assigned;
import restworld.persistence.validation.annotation.AssignedNarrative;
import restworld.persistence.validation.annotation.AssignedNarrativeInAssignedSection;
import restworld.persistence.validation.annotation.AssignedSection;

@Entity
@AssignedNarrativeInAssignedSection
public class Host extends Assigned {
	
	@NotNull
	private FullName name;
	
	@AssignedSection
	@ManyToOne
	private Section section;
	
	@AssignedNarrative
	@ManyToMany
	private Set<Narrative> narratives;

	public FullName getName() {
		return name;
	}

	public void setName(FullName name) {
		this.name = name;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Set<Narrative> getNarratives() {
		return narratives;
	}

	public void setNarratives(Set<Narrative> narratives) {
		this.narratives = narratives;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((narratives == null) ? 0 : narratives.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Host other = (Host) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (narratives == null) {
			if (other.narratives != null)
				return false;
		} else if (!narratives.equals(other.narratives))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}

}
