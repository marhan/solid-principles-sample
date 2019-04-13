package de.marhan.sample.solid.domain.well;

public class WellApartmentNotFoundException extends RuntimeException {


	public WellApartmentNotFoundException(String message) {
		super(message);
	}
}
