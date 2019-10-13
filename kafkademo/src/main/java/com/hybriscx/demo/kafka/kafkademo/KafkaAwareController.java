package com.hybriscx.demo.kafka.kafkademo;

import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;

public abstract class KafkaAwareController {
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;

	/**
	 * Gets the kafka template.
	 *
	 * @return the kafka template
	 */
	public KafkaTemplate<String, String> getKafkaTemplate() {
		return kafkaTemplate;
	}

	/**
	 * Gets the consumer factory instance.
	 *
	 * @return the consumer factory instance
	 */
	public ConsumerFactory<String, Object> getConsumerFactoryInstance() {
		Map<String, Object> configs = new java.util.HashMap<>();
		configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		configs.put(ConsumerConfig.GROUP_ID_CONFIG, "anyIdForGroup");
		configs.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 15000);
		configs.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 1000);
		ConsumerFactory<String, Object> consumerFactory = new DefaultKafkaConsumerFactory<>(configs);
		return consumerFactory;
	}
}
