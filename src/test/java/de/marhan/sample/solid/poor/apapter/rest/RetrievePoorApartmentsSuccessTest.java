package de.marhan.sample.solid.poor.apapter.rest;

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
class RetrievePoorApartmentsSuccessTest {

	private static final int ENTITY_ID_1 = 1;
	private static final int ENTITY_ID_2 = 2;

	@LocalServerPort
	int serverPort;

	private RequestSpecification given;

	@BeforeEach
	void setUp() {
		given = new RestAssuredHelper(serverPort).given();
	}


	@Test
	void whenRetrieveAllPoorApartmentsThenFound() {
		given.when()
				.get("/poor/apartments")
				.then().log().all()
				.statusCode(200)
				.body("$", hasSize(2))
				.body("findAll { it.id == " + ENTITY_ID_1 + "}.city", hasItems("Frankfurt am Main"))
				.body("findAll { it.id == " + ENTITY_ID_1 + "}.street", hasItems("Breitenbachstraße 3"))
				.body("findAll { it.id == " + ENTITY_ID_1 + "}.status", hasItems("free"))
				.body("findAll { it.id == " + ENTITY_ID_2 + "}.city", hasItems("Bremen"))
				.body("findAll { it.id == " + ENTITY_ID_2 + "}.street", hasItems("Havemannstraße 3"))
				.body("findAll { it.id == " + ENTITY_ID_2 + "}.status", hasItems("rented"));
	}


	@Test
	void whenRetrievePoorApartmentByApartmentIdThenFound() {
		given.pathParam("id", ENTITY_ID_1)
				.when()
				.get("/poor/apartments/{id}")
				.then().log().all()
				.statusCode(200)
				.body("id", equalTo(ENTITY_ID_1))
				.body("city", equalTo("Frankfurt am Main"))
				.body("street", equalTo("Breitenbachstraße 3"))
				.body("status", equalTo("free"));
	}

}
