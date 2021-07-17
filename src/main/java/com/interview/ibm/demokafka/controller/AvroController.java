package com.interview.ibm.demokafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interview.ibm.demokafka.producer.DemoProducer;

@RestController
public class AvroController {
	@Autowired
    private DemoProducer demoProducer;

    @PostMapping("/customer/{accountId}/{customerId}/{name}/{phoneNumber}")
    public String produceCustomerAvroMessage(
    		@PathVariable String accountId, 
    		@PathVariable String customerId, 
    		@PathVariable String name, 
    		@PathVariable String phoneNumber) {
    	demoProducer.produceCustomerDetails(accountId, customerId, name, phoneNumber);
        return "Sent customer details to consumer";
    }
    
    @PostMapping("/balance/{accountId}/{balance}/{balanceId}")
    public String produceBalanceAvroMessage(
    		@PathVariable String accountId, 
    		@PathVariable Float balance, 
    		@PathVariable String balanceId) {
    	demoProducer.produceBalanceDetails(accountId, balance, balanceId);
        return "Sent customer details to consumer";

    }    

}
