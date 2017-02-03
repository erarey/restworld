package restworld.dto;

import java.util.Set;

import javax.validation.constraints.NotNull;

import restworld.datatype.Reference;
import restworld.validation.group.RequiredFieldsNotNull;

public class SectionDto {
	
	@NotNull(groups = RequiredFieldsNotNull.class)
	private String name;
	
	private Set<Reference> narratives;
	
	private Set<Reference> employees;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Reference> getNarratives() {
		return narratives;
	}

	public void setNarratives(Set<Reference> narratives) {
		this.narratives = narratives;
	}

	public Set<Reference> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Reference> employees) {
		this.employees = employees;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employees == null) ? 0 : employees.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((narratives == null) ? 0 : narratives.hashCode());
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
		SectionDto other = (SectionDto) obj;
		if (employees == null) {
			if (other.employees != null)
				return false;
		} else if (!employees.equals(other.employees))
			return false;
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
		return true;
	}

}
