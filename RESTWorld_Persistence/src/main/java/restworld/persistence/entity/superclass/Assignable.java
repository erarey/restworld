package restworld.persistence.entity.superclass;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public abstract class Assignable {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToMany
	Set<Assigned> assigned;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Assigned> getAssigned() {
		return assigned;
	}

	public void setAssigned(Set<Assigned> assigned) {
		this.assigned = assigned;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assigned == null) ? 0 : assigned.hashCode());
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
		Assignable other = (Assignable) obj;
		if (assigned == null) {
			if (other.assigned != null)
				return false;
		} else if (!assigned.equals(other.assigned))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
