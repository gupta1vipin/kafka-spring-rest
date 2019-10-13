package com.hybriscx.demo.kafka.kafkademo.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.hybriscx.demo.kafka.kafkademo.KafkaAwareController;

@Service
public class KafkaMessageConsumer extends KafkaAwareController{

	@KafkaListener(topics = {"sampleTopic"})
	public void listenMessage(String message) {
		System.out.println("Below message is recieved from configured topic... Use this to send push notification or elsewhere.");
		System.out.println(message);
	}
}
