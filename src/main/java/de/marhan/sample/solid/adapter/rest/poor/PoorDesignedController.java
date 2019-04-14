package de.marhan.sample.solid.adapter.rest.poor;

import java.util.List;
import java.util.stream.Collectors;

import de.marhan.sample.solid.domain.poor.PoorApartment;
import de.marhan.sample.solid.domain.poor.PoorApartmentService;
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
public class PoorDesignedController {

	private final PoorApartmentService apartmentService;
	private PoorApartmentStatusMapper poorApartmentStatusMapper;

	@Autowired
	public PoorDesignedController(PoorApartmentService apartmentService) {
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
		return apartmentService.findById(entityId)
				.map(this::mapApartmentToResource)
				.map(resource -> new ResponseEntity<>(resource, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/* Violation of SRP */
	private List<PoorApartmentResource> mapApartmentsToResources(List<PoorApartment> apartmentList) {
		return apartmentList.stream().map(this::mapApartmentToResource).collect(Collectors.toList());
	}

	/* Violation of SRP */
	private PoorApartmentResource mapApartmentToResource(PoorApartment apartment) {
		PoorApartmentResource apartmentResource = new PoorApartmentResource();
		apartmentResource.setCity(apartment.getCity());
		apartmentResource.setStreet(apartment.getStreet());
		apartmentResource.setStatus(getStatusMapper().mapToResourceStatus(apartment.getStatus()));
		apartmentResource.setId(apartment.getId());
		return apartmentResource;
	}

	/*
	 * Lazy initialization.
	 *
	 * Violation of SoC
	 */
	private PoorApartmentStatusMapper getStatusMapper() {
		if (poorApartmentStatusMapper == null) {
			poorApartmentStatusMapper = new PoorApartmentStatusMapper();
		}
		return poorApartmentStatusMapper;
	}

}
