package restworld.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import restworld.datatype.Reference;
import restworld.dto.EmployeeDto;
import restworld.mapper.annotation.EmployeeCredentials;
import restworld.persistence.entity.Employee;

@Mapper(componentModel = "spring", uses = {ReferenceMapper.class, CredentialsMapper.class})
public interface EmployeeMapper {

    @Mappings({
            @Mapping(source = "name.first", target = "firstName"),
            @Mapping(source = "name.middle", target = "middleName"),
            @Mapping(source = "name.last", target = "lastName")
    })
    EmployeeDto toEmployeeDto(Employee employee);


    @Mappings({
            @Mapping(source = "firstName", target = "name.first"),
            @Mapping(source = "middleName", target = "name.middle"),
            @Mapping(source = "lastName", target = "name.last"),
            @Mapping(target = "credentials", qualifiedBy = EmployeeCredentials.class)
    })
    Employee toEmployee(EmployeeDto employeeDto);
}
