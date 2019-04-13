package de.marhan.sample.solid.apapter.rest.well;

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
class RetrieveWellApartmentsTest {

	@LocalServerPort
	int serverPort;

	private RequestSpecification given;


	@BeforeEach
	void setUp() {
		given = new RestAssuredHelper(serverPort).given();
	}

	@Test
	@DisplayName("retrieve all apartments with endpoint well/apartments")
	void retrieveAllWellApartments() {
		given.when()
				.get("/well/apartments")
				.then().log().all()
				.statusCode(200)
				.body("$", hasSize(2))
				.body("findAll { it.apartmentId == 'a81b0db2-7114-4f35-8776-751acb3730ca'}.city", hasItems("Hamburg"))
				.body("findAll { it.apartmentId == 'a81b0db2-7114-4f35-8776-751acb3730ca'}.street", hasItems("Helmholtzstraße 2"))
				.body("findAll { it.apartmentId == 'a81b0db2-7114-4f35-8776-751acb3730ca'}.status", hasItems("free"))
				.body("findAll { it.apartmentId == '6226560e-2e9a-4dad-8854-f996ed47e250'}.city", hasItems("Berlin"))
				.body("findAll { it.apartmentId == '6226560e-2e9a-4dad-8854-f996ed47e250'}.street", hasItems("Westarpstraße 3"))
				.body("findAll { it.apartmentId == '6226560e-2e9a-4dad-8854-f996ed47e250'}.status", hasItems("rented"));
	}


	@Test
	@DisplayName("retrieve specific apartment with endpoint well/apartments")
	void retrieveWellApartmentByApartmentId() {
		given.pathParam("apartmentId", "a81b0db2-7114-4f35-8776-751acb3730ca")
				.when()
				.get("/well/apartments/{apartmentId}")
				.then().log().all()
				.statusCode(200)
				.body("apartmentId", equalTo("a81b0db2-7114-4f35-8776-751acb3730ca"))
				.body("city", equalTo("Hamburg"))
				.body("street", equalTo("Helmholtzstraße 2"))
				.body("status", equalTo("free"));
	}

}
