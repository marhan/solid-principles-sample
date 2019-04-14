package de.marhan.sample.solid.adapter.rest.poor;

import de.marhan.sample.solid.domain.poor.PoorApartmentStatus;

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
