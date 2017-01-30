package restworld.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import restworld.dto.EmployeeDto;
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
	public EmployeeDto getById(@PathVariable Long id, HttpServletResponse httpResponse) {
		EmployeeDto result = employeeService.get(id);
		if(result == null)
			httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return result;
	}
	
	@PostMapping
	public Long getById(@RequestBody EmployeeDto employee, HttpServletResponse httpResponse) {
		Long id = employeeService.post(employee);
		return id;
	}

}
