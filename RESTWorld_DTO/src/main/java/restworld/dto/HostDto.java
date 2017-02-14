package restworld.dto;

import java.util.Set;

import javax.validation.constraints.NotNull;

import restworld.datatype.Reference;
import restworld.persistence.entity.Narrative;
import restworld.persistence.entity.Section;
import restworld.validation.group.RequiredFieldsNotNull;

public class HostDto {

	private Long id;
	
	@NotNull(groups = RequiredFieldsNotNull.class)
	private String firstName;
	
	private String middleName;
	
	private String lastName;
	
	@NotNull(groups = RequiredFieldsNotNull.class)
	private Reference<Section, Long> section;
	
	private Set<Reference<Narrative, Long>> narratives;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Reference<Section, Long> getSection() {
		return section;
	}

	public void setSection(Reference<Section, Long> section) {
		this.section = section;
	}

	public Set<Reference<Narrative, Long>> getNarratives() {
		return narratives;
	}

	public void setNarratives(Set<Reference<Narrative, Long>> narratives) {
		this.narratives = narratives;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		HostDto hostDto = (HostDto) o;

		if (id != null ? !id.equals(hostDto.id) : hostDto.id != null) return false;
		if (firstName != null ? !firstName.equals(hostDto.firstName) : hostDto.firstName != null) return false;
		if (middleName != null ? !middleName.equals(hostDto.middleName) : hostDto.middleName != null) return false;
		if (lastName != null ? !lastName.equals(hostDto.lastName) : hostDto.lastName != null) return false;
		if (section != null ? !section.equals(hostDto.section) : hostDto.section != null) return false;
		return narratives != null ? narratives.equals(hostDto.narratives) : hostDto.narratives == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
		result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		result = 31 * result + (section != null ? section.hashCode() : 0);
		result = 31 * result + (narratives != null ? narratives.hashCode() : 0);
		return result;
	}
}
