package de.marhan.sample.solid.domain.well;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WellApartmentService {

    private final WellApartmentRepository apartmentRepository;

    @Autowired
    public WellApartmentService(WellApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    public List<WellApartment> retrieveApartments() {
        return apartmentRepository.findAll();
    }

    public void reserveApartment(UUID apartmentId) {
        WellApartment apartment = apartmentRepository.findByApartmentId(apartmentId);
        apartment.reserve();
        apartmentRepository.save(apartment);
    }


    public void rentApartment(UUID apartmentId) {
        WellApartment apartment = apartmentRepository.findByApartmentId(apartmentId);
        apartment.rent();
        apartmentRepository.save(apartment);
    }

    public void cancelApartment(UUID apartmentId) {
        WellApartment apartment = apartmentRepository.findByApartmentId(apartmentId);
        apartment.cancel();
        apartmentRepository.save(apartment);
    }

    public List<WellApartment> findAll() {
        return apartmentRepository.findAll();
    }

    public WellApartment findByApartmentId(UUID apartmentId) {
        return apartmentRepository.findByApartmentId(apartmentId);
    }

    public WellApartment update(UUID apartmentId, String street, String city) {
        WellApartment apartment = apartmentRepository.findByApartmentId(apartmentId);
        apartment.setStreet(street);
        apartment.setCity(city);
        return apartmentRepository.save(apartment);
    }
}
