package restworld.persistence.entity.superclass;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Assigned {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToMany
	private Set<Assignable> assignments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Assignable> getAssignments() {
		return assignments;
	}

	public void setAssignments(Set<Assignable> assignments) {
		this.assignments = assignments;
	}
	
}
