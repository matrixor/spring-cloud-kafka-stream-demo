package com.interview.ibm.demokafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;

import com.interview.ibm.demokafka.consumer.DemoConsumer;
//import org.springframework.cloud.stream.schema.client.EnableSchemaRegistryClient;

@SpringBootApplication
//@EnableBinding({Processor.class})
@EnableBinding({DemoConsumer.StreamProcessor1.class, DemoConsumer.StreamProcessor2.class})
//@EnableSchemaRegistryClient
public class DemokafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemokafkaApplication.class, args);
	}
}
