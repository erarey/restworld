package restworld.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restworld.persistence.entity.Section;

public interface SectionRepository extends JpaRepository<Section, Long>{

}
