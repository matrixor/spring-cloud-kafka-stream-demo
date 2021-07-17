package com.interview.ibm.demokafka.producer;


import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.JoinWindows;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.ValueJoiner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.ibm.gbs.schema.Customer;
import com.ibm.gbs.schema.CustomerBalance;
import com.ibm.gbs.schema.Transaction;
import com.interview.ibm.demokafka.consumer.DemoConsumer;

@Service
public class DemoProducer {
	@Autowired
	private DemoConsumer.StreamProcessor1 processor1;
	
	@Autowired
	private DemoConsumer.StreamProcessor2 processor2;
	
	//@Autowired
	//private DemoConsumer.StreamProcessor3 processor3;
	
	public void produceCustomerDetails(String accountId, String customerId, String name, String phoneNumber) {
		Customer customer = new Customer();
		customer.setAccountId(accountId);
		customer.setCustomerId(customerId);
		customer.setName(name);
		customer.setPhoneNumber(phoneNumber);

        // creating partition key for kafka topic
        //CustomerKey customerKey = new CustomerKey();
        //customerKey.setAccountId(accountId);
        //customerKey.setName(name);

        Message<Customer> message = MessageBuilder.withPayload(customer)
            .setHeader(KafkaHeaders.MESSAGE_KEY, customerId)
            .build();

        processor1.output().send(message);
        //processor1.outputProcessed();
        
    }
	
	public void produceBalanceDetails(String accountId, Float balance, String balanceId) {
		Transaction transaction = new Transaction();
		transaction.setAccountId(accountId);
		transaction.setBalance(balance);
		transaction.setBalanceId(balanceId);

        Message<Transaction> message = MessageBuilder.withPayload(transaction)
        	.setHeader(KafkaHeaders.MESSAGE_KEY, accountId)
            .build();

        processor2.output().send(message);
        
    }
	
	public void produceJoinDetails(Customer customerDetails) {
        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, Customer> left = builder.stream("Customer");
        KStream<String, Transaction> right = builder.stream("Balance");
        KStream<String, CustomerBalance> all;
        
 
        //all =  left.selectKey((key, value) -> value.getAccountId()).join(right, 
        all =  left.join(right, 
        		new ValueJoiner<Customer, Transaction, CustomerBalance>() {
		        	@Override
		            public CustomerBalance apply(Customer customer, Transaction transaction) {
		        		CustomerBalance joined = new CustomerBalance();
		        		joined.setAccountId(customer.getAccountId());
		        		joined.setCustomerId(customer.getCustomerId());
		        		joined.setPhoneNumber(customer.getPhoneNumber());
		        		joined.setBalance(transaction.getBalance());
		        		
		                return joined;
		        	}
		        }, JoinWindows.of(30000));
        
        System.out.println(all.toString());
        
        //Message<KStream<String, CustomerBalance> > message = MessageBuilder.withPayload(all);
            	//.setHeader(KafkaHeaders.MESSAGE_KEY, all)
        //        .build();
		
        //processor3.outputProcessed(all);      
    }
}
