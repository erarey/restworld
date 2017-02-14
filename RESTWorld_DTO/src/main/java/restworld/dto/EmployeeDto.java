package restworld.dto;

import java.util.Set;

import javax.validation.constraints.NotNull;

import restworld.datatype.Reference;
import restworld.persistence.entity.Employee;
import restworld.persistence.entity.Narrative;
import restworld.persistence.entity.Section;
import restworld.validation.group.RequiredFieldsNotNull;

public class EmployeeDto {

	private Long id;
	
	@NotNull(groups = RequiredFieldsNotNull.class)
	private String firstName;
	
	private String middleName;
	
	@NotNull(groups = RequiredFieldsNotNull.class)
	private String lastName;
	
	@NotNull(groups = RequiredFieldsNotNull.class)
	private CredentialsDto credentials;
	
	private Reference<Employee, Long> manager;
	
	private Set<Reference<Employee, Long>> subordinates;
	
	private Reference<Narrative, Long> directedNarrative;
	
	@NotNull(groups = RequiredFieldsNotNull.class)
	private Reference<Section, Long> section;

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

	public CredentialsDto getCredentials() {
		return credentials;
	}

	public void setCredentials(CredentialsDto credentials) {
		this.credentials = credentials;
	}

	public Reference<Employee, Long> getManager() {
		return manager;
	}

	public void setManager(Reference<Employee, Long> manager) {
		this.manager = manager;
	}

	public Set<Reference<Employee, Long>> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(Set<Reference<Employee, Long>> subordinates) {
		this.subordinates = subordinates;
	}

	public Reference<Narrative, Long> getDirectedNarrative() {
		return directedNarrative;
	}

	public void setDirectedNarrative(Reference<Narrative, Long> directedNarrative) {
		this.directedNarrative = directedNarrative;
	}

	public Reference<Section, Long> getSection() {
		return section;
	}

	public void setSection(Reference<Section, Long> section) {
		this.section = section;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		EmployeeDto that = (EmployeeDto) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
		if (middleName != null ? !middleName.equals(that.middleName) : that.middleName != null) return false;
		if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
		if (credentials != null ? !credentials.equals(that.credentials) : that.credentials != null) return false;
		if (manager != null ? !manager.equals(that.manager) : that.manager != null) return false;
		if (subordinates != null ? !subordinates.equals(that.subordinates) : that.subordinates != null) return false;
		if (directedNarrative != null ? !directedNarrative.equals(that.directedNarrative) : that.directedNarrative != null)
			return false;
		return section != null ? section.equals(that.section) : that.section == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
		result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		result = 31 * result + (credentials != null ? credentials.hashCode() : 0);
		result = 31 * result + (manager != null ? manager.hashCode() : 0);
		result = 31 * result + (subordinates != null ? subordinates.hashCode() : 0);
		result = 31 * result + (directedNarrative != null ? directedNarrative.hashCode() : 0);
		result = 31 * result + (section != null ? section.hashCode() : 0);
		return result;
	}
}
