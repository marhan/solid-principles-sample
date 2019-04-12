package de.marhan.sample.solid.apapter.rest;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RetrieveApartmentsTest {

	@LocalServerPort
	int serverPort;

	private RequestSpecification requestSpecification;

	@BeforeEach
	void setUp() {
		requestSpecification = RestAssured
				.given()
				.log().all()
				.baseUri(determineBaseUri());
	}

	@ParameterizedTest(name = "retrieve apartment by endpoint {arguments}]/apartments")
	@ValueSource(strings = {"well", "poorly"})
	void retrieve(String category) {
		requestSpecification
				.baseUri(determineBaseUri())
				.pathParam("category", category)
				.when()
				.get("/{category}/apartments")
				.then().log().all()
				.statusCode(200)
				.body("$", hasSize(2))
				.body("findAll { it.apartmentId == 'a81b0db2-7114-4f35-8776-751acb3730ca'}.city", hasItems("Hamburg"))
				.body("findAll { it.apartmentId == 'a81b0db2-7114-4f35-8776-751acb3730ca'}.street", hasItems("Helmholtzstraße 2"))
				.body("findAll { it.apartmentId == 'a81b0db2-7114-4f35-8776-751acb3730ca'}.status", hasItems("free"))
				.body("findAll { it.apartmentId == '6226560e-2e9a-4dad-8854-f996ed47e250'}.city", hasItems("Berlin"))
				.body("findAll { it.apartmentId == '6226560e-2e9a-4dad-8854-f996ed47e250'}.street", hasItems("Westarpstraße 3"))
				.body("findAll { it.apartmentId == '6226560e-2e9a-4dad-8854-f996ed47e250'}.status", hasItems("free"));
	}

	private String determineBaseUri() {
		return "http://localhost:" + serverPort;
	}
}
