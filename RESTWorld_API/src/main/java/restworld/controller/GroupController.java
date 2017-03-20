package restworld.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import restworld.dto.GroupDto;
import restworld.dto.GuestDto;
import restworld.persistence.entity.Group;
import restworld.service.GroupService;

@RestController
@Validated
@RequestMapping("group")
//@Api(tags = {"public","groups"}) // ???

public class GroupController {
	
	private GroupService groupService;
	
	public GroupController(GroupService groupService) {
		super();
		this.groupService = groupService;
	}
	
	@GetMapping
	//@ApiOperation(value = "", nickname = "getAllGroups") // ???
	public List<GroupDto> index() {
		return groupService.index();
	}
	
	@GetMapping("{id}")
	public GroupDto getGroupById(String id)
	{
		return groupService.getGroupByName(id);
	}
	
	@GetMapping("{id}")
	public GroupDto getGroupByName(String name)
	{
		return groupService.getGroupByName(name);
	}
	
	@PostMapping
	public Long post(@RequestBody @Validated GroupDto groupDto, HttpServletResponse httpResponse)
	{
		Long id = groupService.post(groupDto);
		httpResponse.setStatus(HttpServletResponse.SC_CREATED);
		return id;
		
	}
	
	@PutMapping("{id}")
	public void put(@PathVariable Long id, @RequestBody @Validated GroupDto groupDto, HttpServletResponse httpResponse) {
		groupService.put(id, groupDto);
		
	}
	//patch
	//delete
	//put
}
