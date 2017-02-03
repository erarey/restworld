package restworld.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import restworld.dto.HostDto;
import restworld.persistence.entity.Host;

@Mapper(componentModel = "spring", uses = { ReferenceMapper.class })
public interface HostMapper {

	
	@Mappings({
			@Mapping(source = "name.first", target = "firstName"),
			@Mapping(source = "name.middle", target = "middleName"),
			@Mapping(source = "name.last", target = "lastName")
	})
	HostDto hostToHostDto(Host host);
	
	
	@Mappings({
		@Mapping(source = "firstName", target = "name.first"),
		@Mapping(source = "middleName", target = "name.middle"),
		@Mapping(source = "lastName", target = "name.last")
	})
	Host hostDtoToHost(HostDto hostDto);
}
