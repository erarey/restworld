package restworld.dto;

import java.util.Set;

import javax.validation.constraints.NotNull;

import restworld.datatype.Reference;
import restworld.persistence.entity.Employee;
import restworld.persistence.entity.Host;
import restworld.persistence.entity.Section;
import restworld.validation.group.RequiredFieldsNotNull;

public class NarrativeDto {

	private Long id;

	@NotNull(groups = RequiredFieldsNotNull.class)
	private String name;
	
	private Set<Reference<Section, Long>> sections;
	
	private Set<Reference<Host, Long>> hosts;
	
	private Reference<Employee, Long> director;

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

	public Set<Reference<Section, Long>> getSections() {
		return sections;
	}

	public void setSections(Set<Reference<Section, Long>> sections) {
		this.sections = sections;
	}

	public Set<Reference<Host, Long>> getHosts() {
		return hosts;
	}

	public void setHosts(Set<Reference<Host, Long>> hosts) {
		this.hosts = hosts;
	}

	public Reference<Employee, Long> getDirector() {
		return director;
	}

	public void setDirector(Reference<Employee, Long> director) {
		this.director = director;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		NarrativeDto that = (NarrativeDto) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		if (sections != null ? !sections.equals(that.sections) : that.sections != null) return false;
		if (hosts != null ? !hosts.equals(that.hosts) : that.hosts != null) return false;
		return director != null ? director.equals(that.director) : that.director == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (sections != null ? sections.hashCode() : 0);
		result = 31 * result + (hosts != null ? hosts.hashCode() : 0);
		result = 31 * result + (director != null ? director.hashCode() : 0);
		return result;
	}
}
