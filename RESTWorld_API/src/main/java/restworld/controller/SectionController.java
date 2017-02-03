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

import restworld.dto.SectionDto;
import restworld.service.SectionService;
import restworld.validation.group.RequiredFieldsNotNull;

@RestController
@Validated
@RequestMapping("section")
public class SectionController {
	
	private SectionService sectionService;

	public SectionController(SectionService sectionService) {
		super();
		this.sectionService = sectionService;
	}
	
	@RequestMapping(method = RequestMethod.HEAD, value = "{id}")
	public void has(@PathVariable Long id, HttpServletResponse httpResponse) {
		if(!sectionService.has(id))
			httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
	}
	
	@GetMapping("{id}")
	public SectionDto get(@PathVariable Long id) {
		return sectionService.get(id);
	}
	
	@PostMapping
	public Long post(@RequestBody @Validated(RequiredFieldsNotNull.class) SectionDto sectionDto, HttpServletResponse httpResponse) {
		Long id = sectionService.post(sectionDto);
		httpResponse.setStatus(HttpServletResponse.SC_CREATED);
		return id;
	}
	
	@PutMapping("{id}")
	public void put(@PathVariable Long id, @RequestBody @Validated(RequiredFieldsNotNull.class) SectionDto sectionDto, HttpServletResponse httpResponse) {
		sectionService.put(id, sectionDto);
	}
	
	@PatchMapping("{id}")
	public void patch(@PathVariable Long id, @RequestBody @Validated SectionDto sectionDto, HttpServletResponse httpResponse) {
		sectionService.patch(id, sectionDto);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id, HttpServletResponse httpResponse) {
		sectionService.delete(id);
	}

}

