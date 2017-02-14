package restworld.mapper;

import org.springframework.stereotype.Component;
import restworld.datatype.Reference;
import restworld.persistence.entity.Role;
import restworld.persistence.repository.RoleRepository;

@Component
public class RoleMapper {
    private final RoleRepository roles;
    private final ReferenceMapper mapper;

    public RoleMapper(RoleRepository roles, ReferenceMapper mapper) {
        this.roles = roles;
        this.mapper = mapper;
    }

    public String toString(Role role) {
        return role.getTitle();
    }

    public Role toRole(String title) {
        return roles.findByTitle(title);
    }
}
