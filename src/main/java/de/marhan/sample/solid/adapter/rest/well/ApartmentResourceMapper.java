package de.marhan.sample.solid.adapter.rest.well;

import java.util.List;
import java.util.stream.Collectors;

import de.marhan.sample.solid.adapter.rest.well.ApartmentResource.Status;
import de.marhan.sample.solid.domain.well.Apartment;
import de.marhan.sample.solid.domain.well.ApartmentStatus;
import org.springframework.stereotype.Component;

@Component
public class ApartmentResourceMapper {

	List<ApartmentResource> mapApartmentsToResources(List<Apartment> apartmentList) {
		return apartmentList.stream().map(this::mapApartmentToResource).collect(Collectors.toList());
	}

	ApartmentResource mapApartmentToResource(Apartment apartment) {
		ApartmentResource apartmentResource = new ApartmentResource();
		apartmentResource.setApartmentId(apartment.getApartmentId());
		apartmentResource.setCity(apartment.getCity());
		apartmentResource.setStreet(apartment.getStreet());
		apartmentResource.setStatus(mapToResourceStatus(apartment.getStatus()));
		return apartmentResource;
	}

	private Status mapToResourceStatus(ApartmentStatus apartmentStatus) {
		Status resourceStatus = null;
		switch (apartmentStatus) {
			case free:
				resourceStatus = Status.free;
				break;
			case reserved:
				resourceStatus = Status.reserved;
				break;
			case rented:
				resourceStatus = Status.rented;
				break;
		}
		return resourceStatus;
	}
}
