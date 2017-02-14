package restworld.mapper;

import org.springframework.stereotype.Component;

import restworld.dto.CredentialsDto;
import restworld.mapper.annotation.EmployeeCredentials;
import restworld.mapper.annotation.GuestCredentials;
import restworld.persistence.entity.Employee;
import restworld.persistence.entity.Guest;
import restworld.persistence.entity.embeddable.Credentials;
import restworld.persistence.repository.EmployeeRepository;
import restworld.persistence.repository.GuestRepository;

import java.util.stream.Collectors;

@Component
public class CredentialsMapper {

    private final RoleMapper roleMapper;
    private final EmployeeRepository employees;
    private final GuestRepository guests;

    public CredentialsMapper(RoleMapper roleMapper, EmployeeRepository employees, GuestRepository guests) {
        this.roleMapper = roleMapper;
        this.employees = employees;
        this.guests = guests;
    }

    public CredentialsDto toCredentialsDto(Credentials credentials) {
        CredentialsDto result = new CredentialsDto();
        result.setUsername(credentials.getUsername());
        result.setRoles(credentials.getRoles().stream().map(roleMapper::toString).collect(Collectors.toSet()));
        return result;
    }

    private Credentials toCredentials(CredentialsDto dto) {
        Credentials credentials = new Credentials();
        credentials.setUsername(dto.getUsername());
        credentials.setPassword(dto.getPassword());
        credentials.setRoles(dto.getRoles().stream().map(roleMapper::toRole).collect(Collectors.toSet()));
        return credentials;
    }

    @EmployeeCredentials
    public Credentials resolveEmployeeCredentials(CredentialsDto dto) {
        Employee employee = employees.findByCredentialsUsername(dto.getUsername());
        return employee != null ? employee.getCredentials() : toCredentials(dto);
    }

    @GuestCredentials
    public Credentials resolveGuestCredentials(CredentialsDto dto) {
        Guest guest = guests.findByCredentialsUsername(dto.getUsername());
        return guest != null ? guest.getCredentials() : toCredentials(dto);
    }

}
