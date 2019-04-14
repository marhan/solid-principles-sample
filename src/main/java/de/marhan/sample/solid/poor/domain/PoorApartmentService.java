package de.marhan.sample.solid.poor.domain;

import java.util.List;
import java.util.Optional;

import de.marhan.sample.solid.poor.adapter.message.PoorKafkaMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PoorApartmentService {

	private final PoorApartmentRepository apartmentRepository;

	/* Violation of DIP */
	private final PoorKafkaMessageProducer messageProducer;

	@Autowired
	public PoorApartmentService(PoorApartmentRepository apartmentRepository, PoorKafkaMessageProducer messageProducer) {
		this.apartmentRepository = apartmentRepository;
		this.messageProducer = messageProducer;
	}

	public List<PoorApartment> findAll() {
		return apartmentRepository.findAll();
	}

	public Optional<PoorApartment> findById(Integer id) {
		return apartmentRepository.findById(id);
	}

	public PoorApartment update(Integer entityId, String street, String city) {

		Optional<PoorApartment> apartmentOptional = apartmentRepository.findById(entityId);

		if (apartmentOptional.isPresent()) {
			PoorApartment apartment = apartmentOptional.get();
			apartment.setStreet(street);
			apartment.setCity(city);
			apartmentRepository.save(apartment);
			messageProducer.sendUpdateEvent(apartment);
			return apartment;
		}

		return null;
	}
}
