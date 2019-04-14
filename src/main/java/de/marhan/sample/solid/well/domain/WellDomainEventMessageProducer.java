package de.marhan.sample.solid.well.domain;

public interface WellDomainEventMessageProducer {

	void sendUpdateEvent(WellApartment apartment);
}
