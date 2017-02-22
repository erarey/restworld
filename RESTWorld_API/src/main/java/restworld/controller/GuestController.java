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
import restworld.dto.GuestDto;
import restworld.service.GuestService;
import restworld.validation.group.RequiredFieldsNotNull;

import java.util.List;

@RestController
@Validated
@RequestMapping("guest")
@Api(tags = {"public", "guests"})
public class GuestController {
	
	private GuestService guestService;

	public GuestController(GuestService guestService) {
		super();
		this.guestService = guestService;
	}

	@GetMapping
	@ApiOperation(value = "", nickname = "getAllGuests")
	public List<GuestDto> index() {
		return guestService.index();
	}

	@GetMapping("sorted")
	@ApiOperation(value = "", nickname = "getSortedGuests")
	public List<GuestDto> sorted(Sort sort) {
		return guestService.sorted(sort);
	}

	@GetMapping("paged")
	@ApiOperation(value = "", nickname = "getPagedGuests")
	public Page<GuestDto> paged(Pageable pageable) {
		return guestService.paged(pageable);
	}

	@PostMapping("search")
	@ApiOperation(value = "", nickname = "searchGuests")
	public List<GuestDto> byExample(GuestDto example) {
		return guestService.byExample(example);
	}
	
	@RequestMapping(method = RequestMethod.HEAD, value = "{id}")
	@ApiOperation(value = "", nickname = "verifyGuest")
	public void has(@PathVariable Long id, HttpServletResponse httpResponse) {
		if(!guestService.has(id))
			httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
	}
	
	@GetMapping("{id}")
	@ApiOperation(value = "", nickname = "getGuest")
	public GuestDto get(@PathVariable Long id) {
		return guestService.get(id);
	}
	
	@PostMapping
	@ApiOperation(value = "", nickname = "createGuest")
	public Long post(@RequestBody @Validated(RequiredFieldsNotNull.class) GuestDto guestDto, HttpServletResponse httpResponse) {
		Long id = guestService.post(guestDto);
		httpResponse.setStatus(HttpServletResponse.SC_CREATED);
		return id;
	}
	
	@PutMapping("{id}")
	@ApiOperation(value = "", nickname = "replaceGuest")
	public void put(@PathVariable Long id, @RequestBody @Validated(RequiredFieldsNotNull.class) GuestDto guestDto, HttpServletResponse httpResponse) {
		guestService.put(id, guestDto);
	}
	
	@PatchMapping("{id}")
	@ApiOperation(value = "", nickname = "updateGuest")
	public void patch(@PathVariable Long id, @RequestBody @Validated GuestDto guestDto, HttpServletResponse httpResponse) {
		guestService.patch(id, guestDto);
	}

	@DeleteMapping("{id}")
	@ApiOperation(value = "", nickname = "deleteGuest")
	public void delete(@PathVariable Long id, HttpServletResponse httpResponse) {
		guestService.delete(id);
	}

}
