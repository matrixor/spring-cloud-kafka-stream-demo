package com.interview.ibm.demokafka.producer;

import java.util.UUID;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.JoinWindows;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.ValueJoiner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ibm.gbs.schema.Customer;
import com.ibm.gbs.schema.CustomerBalance;
import com.ibm.gbs.schema.Transaction;

@Configuration
public class DemoJoinStreams {
	
	@Bean
	public KStream<String, CustomerBalance> process(){

		//KStream<String, Customer>  , KStream<String, Balance>
		StreamsBuilder builder = new StreamsBuilder();
        KStream<String, Customer> left = builder.stream("Customer");
        KStream<String, Transaction> right = builder.stream("Balance");
		KStream<String, CustomerBalance> join;
		
		//all =  left.selectKey((key, value) -> value.getAccountId()).join(right, 
		join =  left.join(right, 
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
        
        System.out.println(join.toString());
		
		return join;
		
	}
	
	/*
	  @Bean("customerStreamTopology")
	  public KStream<String, Long> startProcessing(@Qualifier("customerStreamBuilder") StreamsBuilder builder) {

	    final KStream<String, Long> toSquare = builder.stream("toSquare", Consumed.with(Serdes.String(), Serdes.Long()));
	    toSquare.map((key, value) -> { // do something with each msg, square the values in our case
	      return KeyValue.pair(key, value * value);
	    }).to("squared", Produced.with(Serdes.String(), Serdes.Long())); // send downstream to another topic

	    return toSquare;
	  }
	  */

}
