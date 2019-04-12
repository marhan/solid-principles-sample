package de.marhan.sample.solid.domain.well;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Integer> {

    public Apartment findByApartmentId(UUID apartmentId);
    
}
