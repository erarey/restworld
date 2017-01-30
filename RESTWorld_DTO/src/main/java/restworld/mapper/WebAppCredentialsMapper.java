package restworld.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import restworld.persistence.entity.Employee;
import restworld.persistence.entity.embeddable.WebAppCredentials;
import restworld.persistence.repository.EmployeeRepository;

@Component
public class WebAppCredentialsMapper {

	@Autowired
	EmployeeRepository employeeRepository;
	
	public String asString(WebAppCredentials webAppCredentials) {
		return webAppCredentials.getUsername();
	}
	
	public WebAppCredentials resolve(String userName) {
		Employee target;
		return userName != null ? (target = employeeRepository.findByCredentialsUsername(userName)) != null ? target.getCredentials() : new WebAppCredentials(userName) : null;
	}
	
}
