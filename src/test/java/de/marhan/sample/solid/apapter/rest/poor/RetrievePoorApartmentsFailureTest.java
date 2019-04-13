package de.marhan.sample.solid.apapter.rest.poor;

import de.marhan.sample.solid.apapter.rest.RestAssuredHelper;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RetrievePoorApartmentsFailureTest {

	private static final String INVALID_ENTITY_ID = "INVALID";
	private static final int UNKNOWN_ENTITY_ID = 66;

	@LocalServerPort
	int serverPort;

	private RequestSpecification given;

	@BeforeEach
	void setUp() {
		given = new RestAssuredHelper(serverPort).given();
	}


	@Test
	void whenTryToRetrieveUnknownApartmentByApartmentIdThenNotFound() {
		given.pathParam("id", UNKNOWN_ENTITY_ID)
				.when()
				.get("/poor/apartments/{id}")
				.then().log().all()
				.statusCode(404);
	}

	@Test
	void whenTryToRetrieveApartmentByInvalidApartmentIdThenBadRequest() {
		given.pathParam("id", INVALID_ENTITY_ID)
				.when()
				.get("/poor/apartments/{id}")
				.then().log().all()
				.statusCode(400);
	}

}
