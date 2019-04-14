package de.marhan.sample.solid.well.adapter.message;

import de.marhan.sample.solid.well.domain.WellApartment;
import de.marhan.sample.solid.well.domain.WellDomainEventMessageProducer;
import org.springframework.stereotype.Component;

@Component
public class WellKafkaMessageProducer implements WellDomainEventMessageProducer {

	@Override
	public void sendUpdateEvent(WellApartment apartment) {
		// dummy method
	}
}
