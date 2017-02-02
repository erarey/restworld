package restworld.service;

import org.springframework.stereotype.Service;

import restworld.component.ServiceUtilities;
import restworld.dto.EmployeeDto;
import restworld.mapper.EmployeeMapper;
import restworld.persistence.entity.Employee;
import restworld.persistence.repository.EmployeeRepository;
import restworld.component.ServiceUtilities.IdChecker;

@Service
public class EmployeeService {
	
	EmployeeRepository employeeRepository;
	EmployeeMapper employeeMapper;
	ServiceUtilities serviceUtilities;
	IdChecker idChecker;
	
	public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper, ServiceUtilities serviceUtilities) {
		super();
		this.employeeRepository = employeeRepository;
		this.employeeMapper = employeeMapper;
		this.serviceUtilities = serviceUtilities;
		this.idChecker = serviceUtilities.buildIdChecker(Employee.class, this::has);
	}

	public EmployeeDto get(Long id) {
		idChecker.exists(id);
		return employeeMapper.employeeToEmployeeDto(employeeRepository.findOne(id));
	}

	public Long post(EmployeeDto employeeDto) {
		return employeeRepository.save(employeeMapper.employeeDtoToEmployee(employeeDto)).getId();
	}

	public boolean has(Long id) {
		if(id != null)
			return employeeRepository.exists(id);
		return false;
	}

	public void put(Long id, EmployeeDto employeeDto) {
		idChecker.exists(id);
		Employee employee = employeeMapper.employeeDtoToEmployee(employeeDto);
		employee.setId(id);
		employeeRepository.save(employee);
	}
	
	public void patch(Long id, EmployeeDto employeeDto) {
		idChecker.exists(id);
		employeeRepository.save(serviceUtilities.copyNonNullProperties(employeeMapper.employeeDtoToEmployee(employeeDto), employeeRepository.findOne(id)));
	}
	
	public void delete(Long id) {
		idChecker.exists(id);
		employeeRepository.delete(id);
	}
}
