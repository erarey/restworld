package restworld.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restworld.persistence.entity.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {

	    Group findByName(String name);

}
