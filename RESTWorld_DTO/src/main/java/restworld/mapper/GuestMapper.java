package restworld.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import restworld.datatype.Reference;
import restworld.dto.GuestDto;
import restworld.mapper.annotation.GuestCredentials;
import restworld.persistence.entity.Employee;
import restworld.persistence.entity.Guest;

@Mapper(componentModel = "spring", uses = {ReferenceMapper.class, CredentialsMapper.class})
public interface GuestMapper {

    @Mappings({
            @Mapping(source = "name.first", target = "firstName"),
            @Mapping(source = "name.middle", target = "middleName"),
            @Mapping(source = "name.last", target = "lastName")
    })
    GuestDto toGuestDto(Guest guest);


    @Mappings({
            @Mapping(source = "firstName", target = "name.first"),
            @Mapping(source = "middleName", target = "name.middle"),
            @Mapping(source = "lastName", target = "name.last"),
            @Mapping(target = "credentials", qualifiedBy = GuestCredentials.class)
    })
    Guest toGuest(GuestDto guestDto);
}

