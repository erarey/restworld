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
import restworld.dto.HostDto;
import restworld.service.HostService;
import restworld.validation.group.RequiredFieldsNotNull;

import java.util.List;

@RestController
@Validated
@RequestMapping("host")
@Api(tags = {"public", "hosts"})
public class HostController {
	
	private HostService hostService;

	public HostController(HostService hostService) {
		super();
		this.hostService = hostService;
	}

	@GetMapping
	@ApiOperation(value = "", nickname = "getAllHosts")
	public List<HostDto> index() {
		return hostService.index();
	}

	@GetMapping("sorted")
	@ApiOperation(value = "", nickname = "getSortedHosts")
	public List<HostDto> sorted(Sort sort) {
		return hostService.sorted(sort);
	}

	@GetMapping("paged")
	@ApiOperation(value = "", nickname = "getPagedHosts")
	public Page<HostDto> paged(Pageable pageable) {
		return hostService.paged(pageable);
	}

	@PostMapping("search")
	@ApiOperation(value = "", nickname = "searchHosts")
	public List<HostDto> byExample(HostDto example) {
		return hostService.byExample(example);
	}
	
	@RequestMapping(method = RequestMethod.HEAD, value = "{id}")
	@ApiOperation(value = "", nickname = "verifyHost")
	public void has(@PathVariable Long id, HttpServletResponse httpResponse) {
		if(!hostService.has(id))
			httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
	}
	
	@GetMapping("{id}")
	@ApiOperation(value = "", nickname = "getHost")
	public HostDto get(@PathVariable Long id) {
		return hostService.get(id);
	}
	
	@PostMapping
	@ApiOperation(value = "", nickname = "createHost")
	public Long post(@RequestBody @Validated(RequiredFieldsNotNull.class) HostDto hostDto, HttpServletResponse httpResponse) {
		Long id = hostService.post(hostDto);
		httpResponse.setStatus(HttpServletResponse.SC_CREATED);
		return id;
	}
	
	@PutMapping("{id}")
	@ApiOperation(value = "", nickname = "replaceHost")
	public void put(@PathVariable Long id, @RequestBody @Validated(RequiredFieldsNotNull.class) HostDto hostDto, HttpServletResponse httpResponse) {
		hostService.put(id, hostDto);
	}
	
	@PatchMapping("{id}")
	@ApiOperation(value = "", nickname = "updateHost")
	public void patch(@PathVariable Long id, @RequestBody @Validated HostDto hostDto, HttpServletResponse httpResponse) {
		hostService.patch(id, hostDto);
	}

	@DeleteMapping("{id}")
	@ApiOperation(value = "", nickname = "deleteHost")
	public void delete(@PathVariable Long id, HttpServletResponse httpResponse) {
		hostService.delete(id);
	}

}
