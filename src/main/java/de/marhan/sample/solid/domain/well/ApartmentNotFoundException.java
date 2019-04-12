package de.marhan.sample.solid.domain.well;

public class  ApartmentNotFoundException extends RuntimeException {


	public ApartmentNotFoundException(String message) {
		super(message);
	}
}
