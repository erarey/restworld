package restworld.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restworld.persistence.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
