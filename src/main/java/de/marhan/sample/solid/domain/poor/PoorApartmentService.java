package de.marhan.sample.solid.domain.poor;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PoorApartmentService {

	private final PoorApartmentRepository apartmentRepository;

	@Autowired
	public PoorApartmentService(PoorApartmentRepository apartmentRepository) {
		this.apartmentRepository = apartmentRepository;
	}

	public List<PoorApartment> retrieveApartments() {
		return apartmentRepository.findAll();
	}

	public void reserveApartment(Integer entityId) {
		PoorApartment apartment = apartmentRepository.getOne(entityId);
		apartment.reserve();
		apartmentRepository.save(apartment);
	}


	public void rentApartment(Integer entityId) {
		PoorApartment apartment = apartmentRepository.getOne(entityId);
		apartment.rent();
		apartmentRepository.save(apartment);
	}

	public void cancelApartment(Integer entityId) {
		PoorApartment apartment = apartmentRepository.getOne(entityId);
		apartment.cancel();
		apartmentRepository.save(apartment);
	}

	public List<PoorApartment> findAll() {
		return apartmentRepository.findAll();
	}

	public Optional<PoorApartment> findById(Integer id) {
		return apartmentRepository.findById(id);
	}

	public PoorApartment update(Integer entityId, String street, String city) {
		PoorApartment apartment = apartmentRepository.getOne(entityId);
		apartment.setStreet(street);
		apartment.setCity(city);
		return apartmentRepository.save(apartment);
	}
}
