package de.marhan.sample.solid.well.apapter.rest;

import de.marhan.sample.solid.RestAssuredHelper;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RetrieveWellApartmentsFailureTest {

	private static final String UNKNOWN_APARTMENT_ID = "BD9A5615-8190-4D41-8D24-BCA2E59AFB05";
	private static final String INVALID_APARTMENT_ID = "INVALID";

	@LocalServerPort
	int serverPort;

	private RequestSpecification given;

	@BeforeEach
	void setUp() {
		given = new RestAssuredHelper(serverPort).given();
	}


	@Test
	void whenTryToRetrieveUnknownApartmentByApartmentIdThenNotFound() {
		given.pathParam("apartmentId", UNKNOWN_APARTMENT_ID)
				.when()
				.get("/well/apartments/{apartmentId}")
				.then().log().all()
				.statusCode(404);
	}

	/* Exception handling */
	@Test
	void whenTryToRetrieveApartmentByInvalidApartmentIdThenBadRequest() {
		given.pathParam("apartmentId", INVALID_APARTMENT_ID)
				.when()
				.get("/well/apartments/{apartmentId}")
				.then().log().all()
				.statusCode(400);
	}

}
