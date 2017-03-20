package restworld.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import restworld.dto.GroupDto;
import restworld.mapper.GroupMapper;
import restworld.persistence.entity.Group;
import restworld.persistence.repository.GroupRepository;

@Service
public class GroupService {

	private GroupRepository groupRepository;
	private GroupMapper groupMapper;

	public GroupService(GroupRepository groupRepository, GroupMapper groupMapper) //utility services?
	{
		super();
		this.groupRepository = groupRepository;
		this.groupMapper = groupMapper;
		
		
	}
	// return this list with IDs intact so that those Groups can have more done with them?
	public List<Group> indexNonDto() {
		return groupRepository.findAll();
	}
	
	public List<GroupDto> index() {
		return groupRepository.findAll().stream()
				.map(groupMapper::toGroupDto)
				.collect(Collectors.toList());
	}
	
	public GroupDto getGroupByName(String name) {
		return groupMapper.toGroupDto(groupRepository.findByName(name));
	}
	
	public GroupDto getGroupById(Long id) {
		return groupMapper.toGroupDto(groupRepository.findOne(id));
	}

	public Long post(GroupDto groupDto) {
		return groupRepository.save(groupMapper.toGroup(groupDto)).getId();
		
	}
	public void put(Long id, GroupDto groupDto) {
		//check if group exists in repo currently!
		Group group = groupMapper.toGroup(groupDto);
		group.setId(id);
		groupRepository.save(group);
		
	}

	public void patch(Long id, GroupDto groupDto)
	{
		//check if group exists!
		Group group = new Group();
		if (groupDto.getName() != null) group.setName(groupDto.getName());
		
		if (groupDto.getSize() != null) group.setSize(groupDto.getSize());
		
	}
	
	public void delete(Long id, GroupDto groupDto)
	{
		if (has(id))
			groupRepository.delete(id);
		
	}
	
	public boolean has(Long id)
	{
		if (id != null)
			return groupRepository.exists(id);
		return false;
		
	}
}
