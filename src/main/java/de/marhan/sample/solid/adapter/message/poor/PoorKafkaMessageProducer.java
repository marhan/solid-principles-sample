package de.marhan.sample.solid.adapter.message.poor;

import de.marhan.sample.solid.domain.poor.PoorApartment;
import org.springframework.stereotype.Component;

@Component
public class PoorKafkaMessageProducer {

	public void sendUpdateEvent(PoorApartment apartment) {
		// dummy method
	}
}
