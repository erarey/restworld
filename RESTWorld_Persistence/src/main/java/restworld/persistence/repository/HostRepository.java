package restworld.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restworld.persistence.entity.Host;

public interface HostRepository extends JpaRepository<Host, Long>{

}
