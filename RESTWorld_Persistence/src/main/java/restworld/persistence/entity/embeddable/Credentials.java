package restworld.persistence.entity.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import restworld.persistence.entity.Role;

import java.util.Set;

@Embeddable
public class Credentials {

	private String username;

	@NotNull
	@Column(nullable = false)
	private String password;

	@NotNull
	@ManyToMany
	private Set<Role> roles;

	public Credentials() {
		// TODO Auto-generated constructor stub
	}

	public Credentials(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Credentials that = (Credentials) o;

		if (username != null ? !username.equals(that.username) : that.username != null) return false;
		if (password != null ? !password.equals(that.password) : that.password != null) return false;
		return roles != null ? roles.equals(that.roles) : that.roles == null;
	}

	@Override
	public int hashCode() {
		int result = username != null ? username.hashCode() : 0;
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (roles != null ? roles.hashCode() : 0);
		return result;
	}
}
