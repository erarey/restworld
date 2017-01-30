package restworld.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import restworld.dto.EmployeeDto;
import restworld.persistence.entity.Employee;

@Mapper(componentModel = "spring", uses = { ReferenceMapper.class, WebAppCredentialsMapper.class })
public interface EmployeeMapper {

	
	@Mappings({
			@Mapping(source = "name.first", target = "firstName"),
			@Mapping(source = "name.middle", target = "middleName"),
			@Mapping(source = "name.last", target = "lastName"),
			@Mapping(source  = "credentials.username", target = "username")
	})
	EmployeeDto employeeToEmployeeDto(Employee employee);
	
	
	@Mappings({
		@Mapping(source = "firstName", target = "name.first"),
		@Mapping(source = "middleName", target = "name.middle"),
		@Mapping(source = "lastName", target = "name.last"),
		@Mapping(source  = "username", target = "credentials")
	})
	Employee employeeDtoToEmployee(EmployeeDto employeeDto);
}
