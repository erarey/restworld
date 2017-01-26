package restworld.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restworld.persistence.entity.superclass.Assignable;

public interface AssignableRepository extends JpaRepository<Assignable, Long>{

}
