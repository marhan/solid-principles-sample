package de.marhan.sample.solid.poor.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoorApartmentRepository extends JpaRepository<PoorApartment, Integer> {


}
