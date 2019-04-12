package de.marhan.sample.solid.adapter.rest.well;

import java.util.List;

import de.marhan.sample.solid.domain.well.Apartment;
import de.marhan.sample.solid.domain.well.ApartmentService;
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
@Api(value = "Well designed functionality")
public class WellDesignedController {

	private final ApartmentService apartmentService;
	private final ApartmentResourceMapper apartmentResourceMapper;

	@Autowired
	public WellDesignedController(ApartmentService apartmentService, ApartmentResourceMapper apartmentResourceMapper) {
		this.apartmentService = apartmentService;
		this.apartmentResourceMapper = apartmentResourceMapper;
	}

	@ApiOperation(value = "View a list of available apartments")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved the list of apartments")
	})
	@GetMapping(path = "api/apartment", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ApartmentResource>> retrieveAll() {
		List<Apartment> apartmentList = apartmentService.findAll();
		List<ApartmentResource> apartmentResourceList = apartmentResourceMapper.mapApartmentsToResources(apartmentList);
		return new ResponseEntity<>(apartmentResourceList, HttpStatus.OK);
	}

}
