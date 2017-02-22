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
import restworld.dto.NarrativeDto;
import restworld.service.NarrativeService;
import restworld.validation.group.RequiredFieldsNotNull;

import java.util.List;

@RestController
@Validated
@RequestMapping("narrative")
@Api(tags = {"public", "narratives"})
public class NarrativeController {
	
	private NarrativeService narrativeService;

	public NarrativeController(NarrativeService narrativeService) {
		super();
		this.narrativeService = narrativeService;
	}

	@GetMapping
	@ApiOperation(value = "", nickname = "getAllNarratives")
	public List<NarrativeDto> index() {
		return narrativeService.index();
	}

	@GetMapping("sorted")
	@ApiOperation(value = "", nickname = "getSortedNarratives")
	public List<NarrativeDto> sorted(Sort sort) {
		return narrativeService.sorted(sort);
	}

	@GetMapping("paged")
	@ApiOperation(value = "", nickname = "getPagedNarratives")
	public Page<NarrativeDto> paged(Pageable pageable) {
		return narrativeService.paged(pageable);
	}

	@PostMapping("search")
	@ApiOperation(value = "", nickname = "searchNarratives")
	public List<NarrativeDto> byExample(NarrativeDto example) {
		return narrativeService.byExample(example);
	}
	
	@RequestMapping(method = RequestMethod.HEAD, value = "{id}")
	@ApiOperation(value = "", nickname = "verifyNarrative")
	public void has(@PathVariable Long id, HttpServletResponse httpResponse) {
		if(!narrativeService.has(id))
			httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
	}
	
	@GetMapping("{id}")
	@ApiOperation(value = "", nickname = "getNarrative")
	public NarrativeDto get(@PathVariable Long id) {
		return narrativeService.get(id);
	}
	
	@PostMapping
	@ApiOperation(value = "", nickname = "createNarrative")
	public Long post(@RequestBody @Validated(RequiredFieldsNotNull.class) NarrativeDto employeeDto, HttpServletResponse httpResponse) {
		Long id = narrativeService.post(employeeDto);
		httpResponse.setStatus(HttpServletResponse.SC_CREATED);
		return id;
	}
	
	@PutMapping("{id}")
	@ApiOperation(value = "", nickname = "replaceNarrative")
	public void put(@PathVariable Long id, @RequestBody @Validated(RequiredFieldsNotNull.class) NarrativeDto employeeDto, HttpServletResponse httpResponse) {
		narrativeService.put(id, employeeDto);
	}
	
	@PatchMapping("{id}")
	@ApiOperation(value = "", nickname = "updateNarrative")
	public void patch(@PathVariable Long id, @RequestBody @Validated NarrativeDto narrativeDto, HttpServletResponse httpResponse) {
		narrativeService.patch(id, narrativeDto);
	}

	@DeleteMapping("{id}")
	@ApiOperation(value = "", nickname = "deleteNarrative")
	public void delete(@PathVariable Long id, HttpServletResponse httpResponse) {
		narrativeService.delete(id);
	}

}
