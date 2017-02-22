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
import restworld.dto.SectionDto;
import restworld.service.SectionService;
import restworld.validation.group.RequiredFieldsNotNull;

import java.util.List;

@RestController
@Validated
@RequestMapping("section")
@Api(tags = {"public", "sections"})
public class SectionController {
	
	private SectionService sectionService;

	public SectionController(SectionService sectionService) {
		super();
		this.sectionService = sectionService;
	}

	@GetMapping
	@ApiOperation(value = "", nickname = "getAllSections")
	public List<SectionDto> index() {
		return sectionService.index();
	}

	@GetMapping("sorted")
	@ApiOperation(value = "", nickname = "getSortedSections")
	public List<SectionDto> sorted(Sort sort) {
		return sectionService.sorted(sort);
	}

	@GetMapping("paged")
	@ApiOperation(value = "", nickname = "getPagedSections")
	public Page<SectionDto> paged(Pageable pageable) {
		return sectionService.paged(pageable);
	}

	@PostMapping("search")
	@ApiOperation(value = "", nickname = "searchSections")
	public List<SectionDto> byExample(SectionDto example) {
		return sectionService.byExample(example);
	}
	
	@RequestMapping(method = RequestMethod.HEAD, value = "{id}")
	@ApiOperation(value = "", nickname = "verifySection")
	public void has(@PathVariable Long id, HttpServletResponse httpResponse) {
		if(!sectionService.has(id))
			httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
	}
	
	@GetMapping("{id}")
	@ApiOperation(value = "", nickname = "getSection")
	public SectionDto get(@PathVariable Long id) {
		return sectionService.get(id);
	}
	
	@PostMapping
	@ApiOperation(value = "", nickname = "createSection")
	public Long post(@RequestBody @Validated(RequiredFieldsNotNull.class) SectionDto sectionDto, HttpServletResponse httpResponse) {
		Long id = sectionService.post(sectionDto);
		httpResponse.setStatus(HttpServletResponse.SC_CREATED);
		return id;
	}
	
	@PutMapping("{id}")
	@ApiOperation(value = "", nickname = "replaceSection")
	public void put(@PathVariable Long id, @RequestBody @Validated(RequiredFieldsNotNull.class) SectionDto sectionDto, HttpServletResponse httpResponse) {
		sectionService.put(id, sectionDto);
	}
	
	@PatchMapping("{id}")
	@ApiOperation(value = "", nickname = "updateSection")
	public void patch(@PathVariable Long id, @RequestBody @Validated SectionDto sectionDto, HttpServletResponse httpResponse) {
		sectionService.patch(id, sectionDto);
	}

	@DeleteMapping("{id}")
	@ApiOperation(value = "", nickname = "deleteSection")
	public void delete(@PathVariable Long id, HttpServletResponse httpResponse) {
		sectionService.delete(id);
	}

}

