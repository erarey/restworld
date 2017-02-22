package restworld.controller;

import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
@Api(tags = {"public", "employees"})
public class EmployeeController {
	
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	@GetMapping
	@ApiOperation(value = "", nickname = "getAllEmployees")
	public List<EmployeeDto> index() {
		return employeeService.index();
	}

	@GetMapping("sorted")
	@ApiOperation(value = "", nickname = "getSortedEmployees")
	public List<EmployeeDto> sorted(Sort sort) {
		return employeeService.sorted(sort);
	}

	@GetMapping("paged")
	@ApiOperation(value = "", nickname = "getPagedEmployees")
	public Page<EmployeeDto> paged(Pageable pageable) {
		return employeeService.paged(pageable);
	}

    @PostMapping("search")
    @ApiOperation(value = "", nickname = "searchEmployees")
    public List<EmployeeDto> byExample(EmployeeDto example) {
        return employeeService.byExample(example);
    }

	@RequestMapping(method = RequestMethod.HEAD, value = "{id}")
	@ApiOperation(value = "", nickname = "verifyEmployee")
	public void has(@PathVariable Long id, HttpServletResponse httpResponse) {
		if(!employeeService.has(id))
			httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
	}

	@GetMapping("{id}")
	@ApiOperation(value = "", nickname = "getEmployee")
	public EmployeeDto get(@PathVariable Long id) {
		return employeeService.get(id);
	}

	@PostMapping
	@ApiOperation(value = "", nickname = "createEmployee")
	public Long post(@RequestBody @Validated(RequiredFieldsNotNull.class) EmployeeDto employeeDto, HttpServletResponse httpResponse) {
		Long id = employeeService.post(employeeDto);
		httpResponse.setStatus(HttpServletResponse.SC_CREATED);
		return id;
	}
	
	@PutMapping("{id}")
	@ApiOperation(value = "", nickname = "replaceEmployee")
	public void put(@PathVariable Long id, @RequestBody @Validated(RequiredFieldsNotNull.class) EmployeeDto employeeDto, HttpServletResponse httpResponse) {
		employeeService.put(id, employeeDto);
	}
	
	@PatchMapping("{id}")
	@ApiOperation(value = "", nickname = "updateEmployee")
	public void patch(@PathVariable Long id, @RequestBody @Validated EmployeeDto employeeDto, HttpServletResponse httpResponse) {
		employeeService.patch(id, employeeDto);
	}

	@DeleteMapping("{id}")
	@ApiOperation(value = "", nickname = "deleteEmployee")
	public void delete(@PathVariable Long id, HttpServletResponse httpResponse) {
		employeeService.delete(id);
	}

}
