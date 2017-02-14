package restworld.dto;

import javax.validation.constraints.NotNull;

import restworld.validation.group.RequiredFieldsNotNull;

public class GuestDto {

	private Long id;

	@NotNull(groups = RequiredFieldsNotNull.class)
	private String firstName;
	
	private String middleName;
	
	@NotNull(groups = RequiredFieldsNotNull.class)
	private String lastName;
	
	@NotNull(groups = RequiredFieldsNotNull.class)
	private CredentialsDto credentials;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		GuestDto guestDto = (GuestDto) o;

		if (id != null ? !id.equals(guestDto.id) : guestDto.id != null) return false;
		if (firstName != null ? !firstName.equals(guestDto.firstName) : guestDto.firstName != null) return false;
		if (middleName != null ? !middleName.equals(guestDto.middleName) : guestDto.middleName != null) return false;
		if (lastName != null ? !lastName.equals(guestDto.lastName) : guestDto.lastName != null) return false;
		return credentials != null ? credentials.equals(guestDto.credentials) : guestDto.credentials == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
		result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		result = 31 * result + (credentials != null ? credentials.hashCode() : 0);
		return result;
	}
}
