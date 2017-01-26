package restworld.service;

import org.springframework.stereotype.Service;

import restworld.persistence.entity.Employee;
import restworld.persistence.repository.AssignableRepository;
import restworld.persistence.repository.AssignedRepository;
import restworld.persistence.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	EmployeeRepository employeeRepository;
	AssignedRepository assignedRepository;
	AssignableRepository assignableRepository;
	
	public EmployeeService(EmployeeRepository employeeRepository, AssignedRepository assignedRepository,
			AssignableRepository assignableRepository) {
		super();
		this.employeeRepository = employeeRepository;
		this.assignedRepository = assignedRepository;
		this.assignableRepository = assignableRepository;
	}

	public Employee get(Long id) {
		if(id == null)
			return null;
		return employeeRepository.findOne(id);
	}
	
}
