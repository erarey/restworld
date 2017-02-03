package restworld.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import restworld.persistence.entity.embeddable.FullName;
import restworld.persistence.entity.embeddable.WebAppCredentials;
import restworld.persistence.entity.superclass.BaseEntity;

@Entity
public class Guest implements BaseEntity {

	@Id
	@GeneratedValue
	private Long id;
	
	private FullName name;
	
	private WebAppCredentials credentials;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public FullName getName() {
		return name;
	}

	public void setName(FullName name) {
		this.name = name;
	}

	public WebAppCredentials getCredentials() {
		return credentials;
	}

	public void setCredentials(WebAppCredentials credentials) {
		this.credentials = credentials;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((credentials == null) ? 0 : credentials.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Guest other = (Guest) obj;
		if (credentials == null) {
			if (other.credentials != null)
				return false;
		} else if (!credentials.equals(other.credentials))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
