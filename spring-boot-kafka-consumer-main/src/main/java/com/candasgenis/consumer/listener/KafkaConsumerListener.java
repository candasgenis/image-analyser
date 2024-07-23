package com.candasgenis.consumer.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerListener {

	@KafkaListener(topics = "image-topic", groupId = "${spring.kafka.group-id}")
	public void listen(String message) {
		System.out.printf("Received Messasge: [%s] %n", message);
	}
}
