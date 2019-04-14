package de.marhan.sample.solid.poor.adapter.message;

import de.marhan.sample.solid.poor.domain.PoorApartment;
import org.springframework.stereotype.Component;

@Component
public class PoorKafkaMessageProducer {

	public void sendUpdateEvent(PoorApartment apartment) {
		// dummy method
	}
}
