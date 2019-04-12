package de.marhan.sample.solid.adapter.rest.well;

import java.util.List;

import de.marhan.sample.solid.domain.well.WellApartment;
import de.marhan.sample.solid.domain.well.WellApartmentService;
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

	private final WellApartmentService apartmentService;
	private final WellApartmentResourceMapper apartmentResourceMapper;

	@Autowired
	public WellDesignedController(WellApartmentService apartmentService, WellApartmentResourceMapper apartmentResourceMapper) {
		this.apartmentService = apartmentService;
		this.apartmentResourceMapper = apartmentResourceMapper;
	}

	@ApiOperation(value = "Retrieve a list of apartments")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved the list of apartments")
	})
	@GetMapping(path = "well/apartments", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<WellApartmentResource>> retrieveAll() {
		List<WellApartment> apartmentList = apartmentService.findAll();
		List<WellApartmentResource> apartmentResourceList = apartmentResourceMapper.mapApartmentsToResources(apartmentList);
		return new ResponseEntity<>(apartmentResourceList, HttpStatus.OK);
	}

}
