package com.ecommerce.producers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailMessageProducer {
	
	@Value("${topic.name.producer}")
	private String topico;
	
	private final KafkaTemplate<String, String> kafkaTemplate;
	
	public void send(String message) {
		this.kafkaTemplate.send(this.topico, message);
	}

}
