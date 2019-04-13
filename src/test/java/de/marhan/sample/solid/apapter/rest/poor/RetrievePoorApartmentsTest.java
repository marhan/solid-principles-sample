package de.marhan.sample.solid.apapter.rest.poor;

import de.marhan.sample.solid.apapter.rest.RestAssuredHelper;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
class RetrievePoorApartmentsTest {

	@LocalServerPort
	int serverPort;

	private RequestSpecification given;

	@BeforeEach
	void setUp() {
		given = new RestAssuredHelper(serverPort).given();
	}


	@Test
	@DisplayName("retrieve all apartments with endpoint poor/apartments")
	void retrieveAllPoorApartments() {
		given.when()
				.get("/poor/apartments")
				.then().log().all()
				.statusCode(200)
				.body("$", hasSize(2))
				.body("findAll { it.id == 1}.city", hasItems("Frankfurt am Main"))
				.body("findAll { it.id == 1}.street", hasItems("Breitenbachstraße 3"))
				.body("findAll { it.id == 1}.status", hasItems("free"))
				.body("findAll { it.id == 2}.city", hasItems("Bremen"))
				.body("findAll { it.id == 2}.street", hasItems("Havemannstraße 3"))
				.body("findAll { it.id == 2}.status", hasItems("rented"));
	}


	@Test
	@DisplayName("retrieve specific apartment with endpoint /poor/apartments")
	void retrievePoorApartmentByApartmentId() {
		given.pathParam("id", 1)
				.when()
				.get("/poor/apartments/{id}")
				.then().log().all()
				.statusCode(200)
				.body("id", equalTo(1))
				.body("city", equalTo("Frankfurt am Main"))
				.body("street", equalTo("Breitenbachstraße 3"))
				.body("status", equalTo("free"));
	}

}
