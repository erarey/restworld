package restworld.controller;

import javax.servlet.http.HttpServletResponse;

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

import restworld.dto.HostDto;
import restworld.service.HostService;
import restworld.validation.group.RequiredFieldsNotNull;

@RestController
@Validated
@RequestMapping("host")
public class HostController {
	
	private HostService hostService;

	public HostController(HostService hostService) {
		super();
		this.hostService = hostService;
	}
	
	@RequestMapping(method = RequestMethod.HEAD, value = "{id}")
	public void has(@PathVariable Long id, HttpServletResponse httpResponse) {
		if(!hostService.has(id))
			httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
	}
	
	@GetMapping("{id}")
	public HostDto get(@PathVariable Long id) {
		return hostService.get(id);
	}
	
	@PostMapping
	public Long post(@RequestBody @Validated(RequiredFieldsNotNull.class) HostDto hostDto, HttpServletResponse httpResponse) {
		Long id = hostService.post(hostDto);
		httpResponse.setStatus(HttpServletResponse.SC_CREATED);
		return id;
	}
	
	@PutMapping("{id}")
	public void put(@PathVariable Long id, @RequestBody @Validated(RequiredFieldsNotNull.class) HostDto hostDto, HttpServletResponse httpResponse) {
		hostService.put(id, hostDto);
	}
	
	@PatchMapping("{id}")
	public void patch(@PathVariable Long id, @RequestBody @Validated HostDto hostDto, HttpServletResponse httpResponse) {
		hostService.patch(id, hostDto);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id, HttpServletResponse httpResponse) {
		hostService.delete(id);
	}

}
