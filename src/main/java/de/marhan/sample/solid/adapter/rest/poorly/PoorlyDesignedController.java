package de.marhan.sample.solid.adapter.rest.poorly;

import java.util.List;
import java.util.stream.Collectors;

import de.marhan.sample.solid.domain.poorly.PoorlyApartment;
import de.marhan.sample.solid.domain.poorly.PoorlyApartmentService;
import de.marhan.sample.solid.domain.poorly.PoorlyApartmentStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Poorly designed functionality")
public class PoorlyDesignedController {

	private final PoorlyApartmentService apartmentService;

	@Autowired
	public PoorlyDesignedController(PoorlyApartmentService apartmentService) {
		this.apartmentService = apartmentService;
	}


	@ApiOperation(value = "Retrieve a list of apartments")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved the list of apartments")
	})
	@GetMapping(path = "poorly/apartments", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PoorlyApartmentResource>> retrieveAll() {
		List<PoorlyApartment> apartmentList = apartmentService.findAll();
		List<PoorlyApartmentResource> apartmentResourceList = mapApartmentsToResources(apartmentList);
		return new ResponseEntity<>(apartmentResourceList, HttpStatus.OK);
	}

	private List<PoorlyApartmentResource> mapApartmentsToResources(List<PoorlyApartment> apartmentList) {
		return apartmentList.stream().map(this::mapApartmentToResource).collect(Collectors.toList());
	}

	private PoorlyApartmentResource mapApartmentToResource(PoorlyApartment apartment) {
		PoorlyApartmentResource apartmentResource = new PoorlyApartmentResource();
		apartmentResource.setApartmentId(apartment.getApartmentId());
		apartmentResource.setCity(apartment.getCity());
		apartmentResource.setStreet(apartment.getStreet());
		apartmentResource.setStatus(mapToResourceStatus(apartment.getStatus()));
		return apartmentResource;
	}

	private PoorlyApartmentResource.Status mapToResourceStatus(PoorlyApartmentStatus apartmentStatus) {
		PoorlyApartmentResource.Status resourceStatus = null;
		switch (apartmentStatus) {
			case free:
				resourceStatus = PoorlyApartmentResource.Status.free;
				break;
			case reserved:
				resourceStatus = PoorlyApartmentResource.Status.reserved;
				break;
			case rented:
				resourceStatus = PoorlyApartmentResource.Status.rented;
				break;
		}
		return resourceStatus;
	}
}
