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

import restworld.dto.GuestDto;
import restworld.service.GuestService;
import restworld.validation.group.RequiredFieldsNotNull;

@RestController
@Validated
@RequestMapping("guest")
public class GuestController {
	
	private GuestService guestService;

	public GuestController(GuestService guestService) {
		super();
		this.guestService = guestService;
	}
	
	@RequestMapping(method = RequestMethod.HEAD, value = "{id}")
	public void has(@PathVariable Long id, HttpServletResponse httpResponse) {
		if(!guestService.has(id))
			httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
	}
	
	@GetMapping("{id}")
	public GuestDto get(@PathVariable Long id) {
		return guestService.get(id);
	}
	
	@PostMapping
	public Long post(@RequestBody @Validated(RequiredFieldsNotNull.class) GuestDto guestDto, HttpServletResponse httpResponse) {
		Long id = guestService.post(guestDto);
		httpResponse.setStatus(HttpServletResponse.SC_CREATED);
		return id;
	}
	
	@PutMapping("{id}")
	public void put(@PathVariable Long id, @RequestBody @Validated(RequiredFieldsNotNull.class) GuestDto guestDto, HttpServletResponse httpResponse) {
		guestService.put(id, guestDto);
	}
	
	@PatchMapping("{id}")
	public void patch(@PathVariable Long id, @RequestBody @Validated GuestDto guestDto, HttpServletResponse httpResponse) {
		guestService.patch(id, guestDto);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id, HttpServletResponse httpResponse) {
		guestService.delete(id);
	}

}
