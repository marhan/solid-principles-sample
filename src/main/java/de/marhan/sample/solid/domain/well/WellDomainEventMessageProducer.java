package de.marhan.sample.solid.domain.well;

public interface WellDomainEventMessageProducer {

	void sendUpdateEvent(WellApartment apartment);
}
