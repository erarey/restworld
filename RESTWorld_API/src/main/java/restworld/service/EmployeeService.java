package restworld.service;

import org.springframework.stereotype.Service;

import restworld.dto.EmployeeDto;
import restworld.mapper.EmployeeMapper;
import restworld.persistence.repository.AssignableRepository;
import restworld.persistence.repository.AssignedRepository;
import restworld.persistence.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	EmployeeRepository employeeRepository;
	EmployeeMapper employeeMapper;
	AssignedRepository assignedRepository;
	AssignableRepository assignableRepository;
	
	public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper, AssignedRepository assignedRepository,
			AssignableRepository assignableRepository) {
		super();
		this.employeeRepository = employeeRepository;
		this.employeeMapper = employeeMapper;
		this.assignedRepository = assignedRepository;
		this.assignableRepository = assignableRepository;
	}

	public EmployeeDto get(Long id) {
		if(id == null)
			return null;
		return employeeMapper.employeeToEmployeeDto(employeeRepository.findOne(id));
	}

	public Long post(EmployeeDto employeeDto) {
		return employeeRepository.save(employeeMapper.employeeDtoToEmployee(employeeDto)).getId();
	}
	
}
