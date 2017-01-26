package restworld.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import restworld.persistence.entity.Employee;
import restworld.service.EmployeeService;

@RestController
@RequestMapping("employee")
public class EmployeeController {
	
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	@GetMapping("{id}")
	public Employee getById(@PathVariable Long id, HttpServletResponse httpResponse) {
		Employee result = employeeService.get(id);
		if(result == null)
			httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return result;
	}

}
