package restworld.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import restworld.dto.GuestDto;
import restworld.persistence.entity.Guest;

@Mapper(componentModel = "spring", uses = { ReferenceMapper.class, WebAppCredentialsMapper.class })
public interface GuestMapper {

	
	@Mappings({
			@Mapping(source = "name.first", target = "firstName"),
			@Mapping(source = "name.middle", target = "middleName"),
			@Mapping(source = "name.last", target = "lastName"),
			@Mapping(source  = "credentials.username", target = "username")
	})
	GuestDto guestToGuestDto(Guest guest);
	
	
	@Mappings({
		@Mapping(source = "firstName", target = "name.first"),
		@Mapping(source = "middleName", target = "name.middle"),
		@Mapping(source = "lastName", target = "name.last"),
		@Mapping(source  = "username", target = "credentials")
	})
	Guest guestDtoToGuest(GuestDto guestDto);
}

