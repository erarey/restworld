package restworld.persistence.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import restworld.persistence.entity.embeddable.PersonName;
import restworld.persistence.entity.superclass.Assigned;
import restworld.persistence.validation.annotation.AssignedNarrative;
import restworld.persistence.validation.annotation.AssignedNarrativeInAssignedSection;
import restworld.persistence.validation.annotation.AssignedSection;

@Entity
@AssignedNarrativeInAssignedSection
public class Employee extends Assigned {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	private PersonName name;
	
	@ManyToOne
	private Employee manager;
	
	@OneToMany
	private Set<Employee> subordinates;
	
	@AssignedNarrative
	@OneToOne
	private Narrative directedNarrative;
	
	@AssignedSection
	@ManyToOne
	private Section section;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PersonName getName() {
		return name;
	}

	public void setName(PersonName name) {
		this.name = name;
	}
}
