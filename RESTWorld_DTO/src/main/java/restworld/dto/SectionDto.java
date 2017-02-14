package restworld.dto;

import java.util.Set;

import javax.validation.constraints.NotNull;

import restworld.datatype.Reference;
import restworld.persistence.entity.Employee;
import restworld.persistence.entity.Narrative;
import restworld.validation.group.RequiredFieldsNotNull;

public class SectionDto {

	private Long id;
	
	@NotNull(groups = RequiredFieldsNotNull.class)
	private String name;
	
	private Set<Reference<Narrative, Long>> narratives;
	
	private Set<Reference<Employee, Long>> employees;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Reference<Narrative, Long>> getNarratives() {
		return narratives;
	}

	public void setNarratives(Set<Reference<Narrative, Long>> narratives) {
		this.narratives = narratives;
	}

	public Set<Reference<Employee, Long>> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Reference<Employee, Long>> employees) {
		this.employees = employees;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SectionDto that = (SectionDto) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		if (narratives != null ? !narratives.equals(that.narratives) : that.narratives != null) return false;
		return employees != null ? employees.equals(that.employees) : that.employees == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (narratives != null ? narratives.hashCode() : 0);
		result = 31 * result + (employees != null ? employees.hashCode() : 0);
		return result;
	}
}
