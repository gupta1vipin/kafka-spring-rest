package com.hybriscx.demo.kafka.kafkademo.poller;

import java.time.Duration;
import java.util.Collections;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hybriscx.demo.kafka.kafkademo.KafkaAwareController;


@RestController
public class KafkaMessagePoller extends KafkaAwareController {

	/**
	 * Consume message. This controller function polls the messages for a topic available in Kafka.
	 *
	 * @param topic the topic
	 * @return the string
	 */
	@RequestMapping(value = "/producer/consume-message/{topic}", method = { RequestMethod.GET })
	@ResponseBody
	public String consumeMessage(@PathVariable String topic) {
		
		ConsumerFactory<String, Object> consumerFactory = getConsumerFactoryInstance();

		Consumer<String, Object> consumer = consumerFactory.createConsumer();
		
		consumer.subscribe(Collections.singletonList(topic));
		
		// poll messages from last 10 days
		ConsumerRecords<String, Object> consumerRecords = consumer.poll(Duration.ofMinutes(1));

		// print on console or send back as a string/json. Feel free to change controller function implementation for ResponseBody
		consumerRecords.forEach(action -> {
			System.out.println(action.value());
		});
		
		return  "success";
	}
}
