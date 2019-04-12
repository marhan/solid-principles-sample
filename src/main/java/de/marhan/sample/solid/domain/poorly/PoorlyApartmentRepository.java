package de.marhan.sample.solid.domain.poorly;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoorlyApartmentRepository extends JpaRepository<PoorlyApartment, Integer> {

    PoorlyApartment findByApartmentId(UUID apartmentId);
    
}
