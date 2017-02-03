package restworld.persistence.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import restworld.persistence.entity.embeddable.FullName;
import restworld.persistence.entity.superclass.BaseEntity;
import restworld.persistence.validation.annotation.AssignedNarrative;
import restworld.persistence.validation.annotation.AssignedNarrativeInAssignedSection;
import restworld.persistence.validation.annotation.AssignedSection;

@Entity
@AssignedNarrativeInAssignedSection
public class Host implements BaseEntity {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	private FullName name;
	
	@AssignedSection
	@ManyToOne
	private Section section;
	
	@AssignedNarrative
	@ManyToMany
	private Set<Narrative> narratives;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
