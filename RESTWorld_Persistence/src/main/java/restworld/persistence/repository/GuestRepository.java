package restworld.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restworld.persistence.entity.Guest;

public interface GuestRepository extends JpaRepository<Guest, Long> {

    public Guest findByCredentialsUsername(String username);

}
