package de.marhan.sample.solid.adapter.message.well;

import de.marhan.sample.solid.domain.well.WellApartment;
import de.marhan.sample.solid.domain.well.WellDomainEventMessageProducer;
import org.springframework.stereotype.Component;

@Component
public class WellKafkaMessageProducer implements WellDomainEventMessageProducer {

	@Override
	public void sendUpdateEvent(WellApartment apartment) {
		// dummy method
	}
}
