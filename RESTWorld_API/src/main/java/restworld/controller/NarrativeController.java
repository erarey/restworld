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

import restworld.dto.NarrativeDto;
import restworld.service.NarrativeService;
import restworld.validation.group.RequiredFieldsNotNull;

@RestController
@Validated
@RequestMapping("narrative")
public class NarrativeController {
	
	private NarrativeService narrativeService;

	public NarrativeController(NarrativeService narrativeService) {
		super();
		this.narrativeService = narrativeService;
	}
	
	@RequestMapping(method = RequestMethod.HEAD, value = "{id}")
	public void has(@PathVariable Long id, HttpServletResponse httpResponse) {
		if(!narrativeService.has(id))
			httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
	}
	
	@GetMapping("{id}")
	public NarrativeDto get(@PathVariable Long id) {
		return narrativeService.get(id);
	}
	
	@PostMapping
	public Long post(@RequestBody @Validated(RequiredFieldsNotNull.class) NarrativeDto employeeDto, HttpServletResponse httpResponse) {
		Long id = narrativeService.post(employeeDto);
		httpResponse.setStatus(HttpServletResponse.SC_CREATED);
		return id;
	}
	
	@PutMapping("{id}")
	public void put(@PathVariable Long id, @RequestBody @Validated(RequiredFieldsNotNull.class) NarrativeDto employeeDto, HttpServletResponse httpResponse) {
		narrativeService.put(id, employeeDto);
	}
	
	@PatchMapping("{id}")
	public void patch(@PathVariable Long id, @RequestBody @Validated NarrativeDto employeeDto, HttpServletResponse httpResponse) {
		narrativeService.patch(id, employeeDto);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id, HttpServletResponse httpResponse) {
		narrativeService.delete(id);
	}

}
