package com.interview.ibm.demokafka.consumer;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.JoinWindows;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.ValueJoiner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;
import org.springframework.cloud.stream.messaging.Processor;

import com.ibm.gbs.schema.Customer;
import com.ibm.gbs.schema.CustomerBalance;
import com.ibm.gbs.schema.Transaction;
import com.interview.ibm.demokafka.producer.DemoJoinStreams;
import com.interview.ibm.demokafka.producer.DemoProducer;

@Service
public class DemoConsumer {
	
	@Autowired
    private DemoJoinStreams demoJoinStreams;
	
    @StreamListener(StreamProcessor1.INPUT)
    public void consumeCustomerDetails(Customer customerDetails) {
        System.out.println("Let's process customer details: {" +  customerDetails + "}" );
        
        demoJoinStreams.process();
    }
    
    @StreamListener(StreamProcessor2.INPUT)
    public void consumeBalanceDetails(Transaction transactionDetails) {
        System.out.println("Let's process balance details: {" +  transactionDetails + "}" );
    }
 
    /*
    //@SuppressWarnings("deprecation")
	@StreamListener(StreamProcessor3.INPUT)
    @SendTo(StreamProcessor3.OUTPUT)
    public KStream<String, CustomerBalance> consumeJoin(Customer customerDetails) {
        //System.out.println("Stream listening : {" +  input.toString() + "}" );
        
        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, Customer> left = builder.stream("Customer");
        KStream<String, Transaction> right = builder.stream("Balance");
        KStream<String, CustomerBalance> all = null;

        return all;
    }
    */
    
    public interface StreamProcessor1 {

        String INPUT = "input1";
        String OUTPUT = "output1";

        @Input(INPUT)
        SubscribableChannel input();
        //KStream<String, Customer> input();

        @Output(OUTPUT)
        MessageChannel output();
        //KStream<String, Customer> outputProcessed();
    }

    public interface StreamProcessor2 {

        String INPUT = "input2";
        String OUTPUT = "output2";

        @Input(INPUT)
        SubscribableChannel input();
        //KStream<String, Transaction> input();

        @Output(OUTPUT)
        MessageChannel output();
        //KStream<String, Transaction> outputProcessed();
    }

    public interface StreamProcessor3 {

        String INPUT = "input3";
        String OUTPUT = "output3";

        @Input(INPUT)
        KStream<String, Customer> input();

        @Output(OUTPUT)
        KStream<String, CustomerBalance> outputProcessed();
    }
    
    
}
