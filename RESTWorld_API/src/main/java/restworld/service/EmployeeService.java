package restworld.service;

import org.springframework.stereotype.Service;

import restworld.component.ServiceUtilities;
import restworld.dto.EmployeeDto;
import restworld.mapper.EmployeeMapper;
import restworld.persistence.entity.Employee;
import restworld.persistence.repository.EmployeeRepository;
import restworld.component.ServiceUtilities.IdChecker;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
	
	private final EmployeeRepository employeeRepository;
	private final EmployeeMapper employeeMapper;
	private final ServiceUtilities serviceUtilities;
	private final IdChecker idChecker;
	
	public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper, ServiceUtilities serviceUtilities) {
		super();
		this.employeeRepository = employeeRepository;
		this.employeeMapper = employeeMapper;
		this.serviceUtilities = serviceUtilities;
		this.idChecker = serviceUtilities.buildIdChecker(Employee.class, this::has);
	}

	public List<EmployeeDto> index() {
		return employeeRepository
				.findAll()
				.stream()
				.map(employeeMapper::toEmployeeDto)
				.collect(Collectors.toList());
	}

	public EmployeeDto get(Long id) {
		idChecker.exists(id);
		return employeeMapper.toEmployeeDto(employeeRepository.findOne(id));
	}

	public Long post(EmployeeDto employeeDto) {
		return employeeRepository.save(employeeMapper.toEmployee(employeeDto)).getId();
	}

	public boolean has(Long id) {
		if(id != null)
			return employeeRepository.exists(id);
		return false;
	}

	public void put(Long id, EmployeeDto employeeDto) {
		idChecker.exists(id);
		Employee employee = employeeMapper.toEmployee(employeeDto);
		employee.setId(id);
		employeeRepository.save(employee);
	}
	
	public void patch(Long id, EmployeeDto employeeDto) {
		idChecker.exists(id);
		employeeRepository.save(serviceUtilities.copyNonNullProperties(employeeMapper.toEmployee(employeeDto), employeeRepository.findOne(id)));
	}
	
	public void delete(Long id) {
		idChecker.exists(id);
		employeeRepository.delete(id);
	}
}
