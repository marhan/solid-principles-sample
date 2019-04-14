package de.marhan.sample.solid.well.adapter.rest;

import java.util.List;
import java.util.stream.Collectors;

import de.marhan.sample.solid.well.adapter.rest.WellApartmentResource.Status;
import de.marhan.sample.solid.well.domain.WellApartment;
import de.marhan.sample.solid.well.domain.WellApartmentStatus;
import org.springframework.stereotype.Component;

@Component
public class WellApartmentResourceMapper {

	List<WellApartmentResource> mapApartmentsToResources(List<WellApartment> apartmentList) {
		return apartmentList.stream().map(this::mapApartmentToResource).collect(Collectors.toList());
	}

	WellApartmentResource mapApartmentToResource(WellApartment apartment) {
		WellApartmentResource apartmentResource = new WellApartmentResource();
		apartmentResource.setApartmentId(apartment.getApartmentId());
		apartmentResource.setCity(apartment.getCity());
		apartmentResource.setStreet(apartment.getStreet());
		apartmentResource.setStatus(mapToResourceStatus(apartment.getStatus()));
		return apartmentResource;
	}

	private Status mapToResourceStatus(WellApartmentStatus apartmentStatus) {
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
