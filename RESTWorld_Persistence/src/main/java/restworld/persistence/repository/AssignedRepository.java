package restworld.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restworld.persistence.entity.superclass.Assigned;

public interface AssignedRepository extends JpaRepository<Assigned, Long>{

}
