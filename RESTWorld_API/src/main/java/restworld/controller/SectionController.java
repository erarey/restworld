package restworld.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import restworld.dto.SectionDto;
import restworld.service.SectionService;

@RestController
@RequestMapping("section")
public class SectionController {

	SectionService sectionService;
	
	public SectionController(SectionService sectionService) {
		this.sectionService = sectionService;
	}
	
	@GetMapping("{id}")
	public SectionDto get(@PathVariable Long id, HttpServletResponse httpResponse) {
		SectionDto result = sectionService.get(id);
		if(result == null)
			httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return result;
	}
	
	@PostMapping
	public Long post(@RequestBody SectionDto section, HttpServletResponse httpResponse) {
		Long id = sectionService.post(section);
		return id;
	}
}
