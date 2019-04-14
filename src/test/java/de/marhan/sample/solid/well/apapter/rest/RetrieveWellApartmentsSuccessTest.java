package de.marhan.sample.solid.well.apapter.rest;

import de.marhan.sample.solid.RestAssuredHelper;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RetrieveWellApartmentsSuccessTest {

	private static final String APARTMENT_ID_1 = "a81b0db2-7114-4f35-8776-751acb3730ca";
	private static final String APARTMENT_ID_2 = "6226560e-2e9a-4dad-8854-f996ed47e250";

	@LocalServerPort
	int serverPort;

	private RequestSpecification given;


	@BeforeEach
	void setUp() {
		given = new RestAssuredHelper(serverPort).given();
	}

	@Test
	void whenRetrieveAllWellApartmentsThenFound() {
		given.when()
				.get("/well/apartments")
				.then().log().all()
				.statusCode(200)
				.body("$", hasSize(2))
				.body("findAll { it.apartmentId == '" + APARTMENT_ID_1 + "'}.city", hasItems("Hamburg"))
				.body("findAll { it.apartmentId == '" + APARTMENT_ID_1 + "'}.street", hasItems("Helmholtzstraße 2"))
				.body("findAll { it.apartmentId == '" + APARTMENT_ID_1 + "'}.status", hasItems("free"))
				.body("findAll { it.apartmentId == '" + APARTMENT_ID_2 + "'}.city", hasItems("Berlin"))
				.body("findAll { it.apartmentId == '" + APARTMENT_ID_2 + "'}.street", hasItems("Westarpstraße 3"))
				.body("findAll { it.apartmentId == '" + APARTMENT_ID_2 + "'}.status", hasItems("rented"));
	}


	@Test
	void whenRetrieveKnownWellApartmentByApartmentIdThenFound() {
		given.pathParam("apartmentId", APARTMENT_ID_1)
				.when()
				.get("/well/apartments/{apartmentId}")
				.then().log().all()
				.statusCode(200)
				.body("apartmentId", equalTo(APARTMENT_ID_1))
				.body("city", equalTo("Hamburg"))
				.body("street", equalTo("Helmholtzstraße 2"))
				.body("status", equalTo("free"));
	}

}
