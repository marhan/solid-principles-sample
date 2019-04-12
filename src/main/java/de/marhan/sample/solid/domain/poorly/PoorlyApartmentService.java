package de.marhan.sample.solid.domain.poorly;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PoorlyApartmentService {

    private final PoorlyApartmentRepository apartmentRepository;

    @Autowired
    public PoorlyApartmentService(PoorlyApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    public List<PoorlyApartment> retrieveApartments() {
        return apartmentRepository.findAll();
    }

    public void reserveApartment(UUID apartmentId) {
        PoorlyApartment apartment = apartmentRepository.findByApartmentId(apartmentId);
        apartment.reserve();
        apartmentRepository.save(apartment);
    }


    public void rentApartment(UUID apartmentId) {
        PoorlyApartment apartment = apartmentRepository.findByApartmentId(apartmentId);
        apartment.rent();
        apartmentRepository.save(apartment);
    }

    public void cancelApartment(UUID apartmentId) {
        PoorlyApartment apartment = apartmentRepository.findByApartmentId(apartmentId);
        apartment.cancel();
        apartmentRepository.save(apartment);
    }

    public List<PoorlyApartment> findAll() {
        return apartmentRepository.findAll();
    }

    public PoorlyApartment findByApartmentId(UUID apartmentId) {
        return apartmentRepository.findByApartmentId(apartmentId);
    }

    public PoorlyApartment update(UUID apartmentId, String street, String city) {
        PoorlyApartment apartment = apartmentRepository.findByApartmentId(apartmentId);
        apartment.setStreet(street);
        apartment.setCity(city);
        return apartmentRepository.save(apartment);
    }
}
