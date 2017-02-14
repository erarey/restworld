package restworld.controller;

import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import restworld.dto.EmployeeDto;
import restworld.service.EmployeeService;
import restworld.validation.group.RequiredFieldsNotNull;

import java.util.List;

@RestController
@Validated
@RequestMapping("employee")
@Api(tags = "employees")
public class EmployeeController {
	
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	@GetMapping
	@ApiOperation(value = "", nickname = "getAllEmployees")
	public List<EmployeeDto> getAllEmployees() {
		return employeeService.index();
	}

	@RequestMapping(method = RequestMethod.HEAD, value = "{id}")
	@ApiOperation(value = "", nickname = "employeeExistsForId")
	public void has(@PathVariable Long id, HttpServletResponse httpResponse) {
		if(!employeeService.has(id))
			httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
	}

	@GetMapping("{id}")
	@ApiOperation(value = "", nickname = "getEmployeeById")
	public EmployeeDto get(@PathVariable Long id) {
		return employeeService.get(id);
	}

	@PostMapping
	@ApiOperation(value = "", nickname = "postNewEmployee")
	public Long post(@RequestBody @Validated(RequiredFieldsNotNull.class) EmployeeDto employeeDto, HttpServletResponse httpResponse) {
		Long id = employeeService.post(employeeDto);
		httpResponse.setStatus(HttpServletResponse.SC_CREATED);
		return id;
	}
	
	@PutMapping("{id}")
	@ApiOperation(value = "", "")
	public void put(@PathVariable Long id, @RequestBody @Validated(RequiredFieldsNotNull.class) EmployeeDto employeeDto, HttpServletResponse httpResponse) {
		employeeService.put(id, employeeDto);
	}
	
	@PatchMapping("{id}")
	public void patch(@PathVariable Long id, @RequestBody @Validated EmployeeDto employeeDto, HttpServletResponse httpResponse) {
		employeeService.patch(id, employeeDto);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id, HttpServletResponse httpResponse) {
		employeeService.delete(id);
	}

}
