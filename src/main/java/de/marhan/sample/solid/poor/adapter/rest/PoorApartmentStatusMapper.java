package de.marhan.sample.solid.poor.adapter.rest;

import de.marhan.sample.solid.poor.domain.PoorApartmentStatus;

/* weak cohesion */
class PoorApartmentStatusMapper {

	PoorApartmentResource.Status mapToResourceStatus(PoorApartmentStatus apartmentStatus) {
		PoorApartmentResource.Status resourceStatus = null;
		switch (apartmentStatus) {
			case free:
				resourceStatus = PoorApartmentResource.Status.free;
				break;
			case reserved:
				resourceStatus = PoorApartmentResource.Status.reserved;
				break;
			case rented:
				resourceStatus = PoorApartmentResource.Status.rented;
				break;
		}
		return resourceStatus;
	}
}
