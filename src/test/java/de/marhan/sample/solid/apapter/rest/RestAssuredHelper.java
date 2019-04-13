package de.marhan.sample.solid.apapter.rest;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class RestAssuredHelper {

	private Integer serverPort;

	public RestAssuredHelper(Integer serverPort) {
		this.serverPort = serverPort;
	}

	public RequestSpecification given() {
		return RestAssured
				.given()
				.log().all()
				.baseUri(determineBaseUri());
	}

	private String determineBaseUri() {
		return "http://localhost:" + serverPort;
	}
}
