package restworld.persistence.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import restworld.persistence.entity.embeddable.Credentials;
import restworld.persistence.entity.embeddable.FullName;
import restworld.persistence.entity.superclass.BaseEntity;
import restworld.persistence.validation.annotation.AssignedNarrative;
import restworld.persistence.validation.annotation.AssignedNarrativeInAssignedSection;
import restworld.persistence.validation.annotation.AssignedSection;

@Entity
@AssignedNarrativeInAssignedSection
public class Employee implements BaseEntity<Long> {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	private FullName name;
	
	private Credentials credentials;
	
	@ManyToOne
	private Employee manager;
	
	@OneToMany(mappedBy = "manager")
	private Set<Employee> subordinates;
	
	@AssignedNarrative
	@OneToOne
	private Narrative directedNarrative;
	
	@NotNull
	@AssignedSection
	@ManyToOne
	private Section section;
	
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
		Employee other = (Employee) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}
}
