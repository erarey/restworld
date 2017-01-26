package restworld.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restworld.persistence.entity.Narrative;

public interface NarrativeRepository extends JpaRepository<Narrative, Long> {

}
