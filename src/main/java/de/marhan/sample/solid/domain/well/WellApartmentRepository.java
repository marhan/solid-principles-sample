package de.marhan.sample.solid.domain.well;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WellApartmentRepository extends JpaRepository<WellApartment, Integer> {

    Optional<WellApartment> findByApartmentId(UUID apartmentId);
    
}
