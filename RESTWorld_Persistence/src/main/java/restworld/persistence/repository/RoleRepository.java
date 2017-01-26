package restworld.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restworld.persistence.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
