package com.hybriscx.demo.kafka.kafkademo.producer;

import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hybriscx.demo.kafka.kafkademo.KafkaAwareController;

@RestController
public class KafkaMessageProducer extends KafkaAwareController{

	@RequestMapping(value = "/producer/publish-message/{topic}", method = { RequestMethod.POST, RequestMethod.GET })
	public String publishMessage(@PathVariable String topic, @RequestBody String data) {
		getKafkaTemplate().send(topic, data);
		return "Published succcessfully to Kafka";
	}
	
	
	@RequestMapping(value = "/producer/publish-message-with-keyu/{topic}", method = { RequestMethod.POST, RequestMethod.GET })
	public String publishMessageWithKey(@PathVariable String topic, @RequestBody String data) {
		getKafkaTemplate().send(topic, data);
		return "Published succcessfully to Kafka";
	}
	
	
	@RequestMapping(value = "/producer/publish-message-status/{topic}", method = { RequestMethod.POST, RequestMethod.GET })
	public String pubishMessageAndCheckStatus(@PathVariable String topic, @RequestBody String data) {
		
		ListenableFuture<SendResult<String, String>> future = getKafkaTemplate().send(topic, data);
				
		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

		    @Override
		    public void onSuccess(SendResult<String, String> result) {
		        System.out.println("---success----"+result);
		    }

		    @Override
		    public void onFailure(Throwable ex) {
		        System.out.println("---error----"+ex);

		    }
		});
		return "here you can send required status to producer.";
	}
}
