package de.marhan.sample.solid.adapter.rest.poor;

import java.util.List;
import java.util.stream.Collectors;

import de.marhan.sample.solid.domain.poor.PoorApartment;
import de.marhan.sample.solid.domain.poor.PoorApartmentService;
import de.marhan.sample.solid.domain.poor.PoorApartmentStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Poor designed functionality")
public class PoorlyDesignedController {

	private final PoorApartmentService apartmentService;

	@Autowired
	public PoorlyDesignedController(PoorApartmentService apartmentService) {
		this.apartmentService = apartmentService;
	}


	@ApiOperation(value = "Retrieve a list of apartments")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved the list of apartments")
	})
	@GetMapping(path = "poor/apartments", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PoorApartmentResource>> retrieveAll() {
		List<PoorApartment> apartmentList = apartmentService.findAll();
		List<PoorApartmentResource> apartmentResourceList = mapApartmentsToResources(apartmentList);
		return new ResponseEntity<>(apartmentResourceList, HttpStatus.OK);
	}

	@ApiOperation(value = "Retrieve the specified apartment")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved the apartment"),
			@ApiResponse(code = 404, message = "The specified apartment was not found")
	})
	@GetMapping(path = "poor/apartments/{entityId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PoorApartmentResource> retrieve(@PathVariable(name = "entityId") Integer entityId) {
		PoorApartment apartment = apartmentService.findByApartmentId(entityId);
		PoorApartmentResource apartmentResource = mapApartmentToResource(apartment);
		return new ResponseEntity<>(apartmentResource, HttpStatus.OK);
	}

	private List<PoorApartmentResource> mapApartmentsToResources(List<PoorApartment> apartmentList) {
		return apartmentList.stream().map(this::mapApartmentToResource).collect(Collectors.toList());
	}

	private PoorApartmentResource mapApartmentToResource(PoorApartment apartment) {
		PoorApartmentResource apartmentResource = new PoorApartmentResource();
		apartmentResource.setCity(apartment.getCity());
		apartmentResource.setStreet(apartment.getStreet());
		apartmentResource.setStatus(mapToResourceStatus(apartment.getStatus()));
		apartmentResource.setId(apartment.getId());
		return apartmentResource;
	}

	private PoorApartmentResource.Status mapToResourceStatus(PoorApartmentStatus apartmentStatus) {
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
