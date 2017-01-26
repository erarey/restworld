package restworld.persistence.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import restworld.persistence.entity.embeddable.FullName;
import restworld.persistence.entity.embeddable.WebAppCredentials;
import restworld.persistence.entity.superclass.Assigned;
import restworld.persistence.validation.annotation.AssignedNarrative;
import restworld.persistence.validation.annotation.AssignedNarrativeInAssignedSection;
import restworld.persistence.validation.annotation.AssignedSection;

@Entity
@AssignedNarrativeInAssignedSection
public class Employee extends Assigned {
	
	@NotNull
	private FullName name;
	
	private WebAppCredentials credentials;
	
	@ManyToOne
	private Employee manager;
	
	@OneToMany
	private Set<Employee> subordinates;
	
	@AssignedNarrative
	@OneToOne
	private Narrative directedNarrative;
	
	@NotNull
	@AssignedSection
	@ManyToOne
	private Section section;
	
	public FullName getName() {
		return name;
	}

	public void setName(FullName name) {
		this.name = name;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public Set<Employee> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(Set<Employee> subordinates) {
		this.subordinates = subordinates;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Narrative getDirectedNarrative() {
		return directedNarrative;
	}

	public void setDirectedNarrative(Narrative directedNarrative) {
		this.directedNarrative = directedNarrative;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((directedNarrative == null) ? 0 : directedNarrative.hashCode());
		result = prime * result + ((manager == null) ? 0 : manager.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		result = prime * result + ((subordinates == null) ? 0 : subordinates.hashCode());
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
		Employee other = (Employee) obj;
		if (directedNarrative == null) {
			if (other.directedNarrative != null)
				return false;
		} else if (!directedNarrative.equals(other.directedNarrative))
			return false;
		if (manager == null) {
			if (other.manager != null)
				return false;
		} else if (!manager.equals(other.manager))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		if (subordinates == null) {
			if (other.subordinates != null)
				return false;
		} else if (!subordinates.equals(other.subordinates))
			return false;
		return true;
	}

	public WebAppCredentials getCredentials() {
		return credentials;
	}

	public void setCredentials(WebAppCredentials credentials) {
		this.credentials = credentials;
	}
}
