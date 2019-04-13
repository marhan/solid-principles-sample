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

	public List<WellApartment> findAll() {
		return apartmentRepository.findAll();
	}

	public WellApartment findByApartmentId(UUID apartmentId) {
		return apartmentRepository.findByApartmentId(apartmentId).orElseThrow(() -> createNotFoundException(apartmentId));
	}

	public WellApartment update(UUID apartmentId, String street, String city) {
		WellApartment apartment = apartmentRepository.findByApartmentId(apartmentId)
				.orElseThrow(() -> createNotFoundException(apartmentId));

		apartment.setStreet(street);
		apartment.setCity(city);
		return apartmentRepository.save(apartment);
	}

	private WellApartmentNotFoundException createNotFoundException(UUID apartmentId) {
		return new WellApartmentNotFoundException("No apartment with apartmentId '" + apartmentId + "' found.");
	}
}
