package restworld.dto;

import java.util.Set;

import javax.validation.constraints.NotNull;

import restworld.datatype.Reference;
import restworld.validation.group.RequiredFieldsNotNull;

public class EmployeeDto {
	
	@NotNull(groups = RequiredFieldsNotNull.class)
	private String firstName;
	
	private String middleName;
	@NotNull(groups = RequiredFieldsNotNull.class)
	private String lastName;
	
	@NotNull(groups = RequiredFieldsNotNull.class)
	private String username;
	
	private Reference manager;
	
	private Set<Reference> subordinates;
	
	private Reference directedNarrative;
	
	@NotNull(groups = RequiredFieldsNotNull.class)
	private Reference section;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Reference getManager() {
		return manager;
	}

	public void setManager(Reference manager) {
		this.manager = manager;
	}

	public Set<Reference> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(Set<Reference> subordinates) {
		this.subordinates = subordinates;
	}

	public Reference getDirectedNarrative() {
		return directedNarrative;
	}

	public void setDirectedNarrative(Reference directedNarrative) {
		this.directedNarrative = directedNarrative;
	}

	public Reference getSection() {
		return section;
	}

	public void setSection(Reference section) {
		this.section = section;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((directedNarrative == null) ? 0 : directedNarrative.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((manager == null) ? 0 : manager.hashCode());
		result = prime * result + ((middleName == null) ? 0 : middleName.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		result = prime * result + ((subordinates == null) ? 0 : subordinates.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		EmployeeDto other = (EmployeeDto) obj;
		if (directedNarrative == null) {
			if (other.directedNarrative != null)
				return false;
		} else if (!directedNarrative.equals(other.directedNarrative))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (manager == null) {
			if (other.manager != null)
				return false;
		} else if (!manager.equals(other.manager))
			return false;
		if (middleName == null) {
			if (other.middleName != null)
				return false;
		} else if (!middleName.equals(other.middleName))
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
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}
