package com.interview.ibm.demokafka.joiner;

import org.apache.kafka.streams.kstream.ValueJoiner;

import com.ibm.gbs.schema.Customer;
import com.ibm.gbs.schema.CustomerBalance;
import com.ibm.gbs.schema.Transaction;

public class CustomerBalanceJoiner implements ValueJoiner<Customer, Transaction, CustomerBalance> {

	@Override
	public CustomerBalance apply(Customer customer, Transaction transaction) {

		CustomerBalance joined = new CustomerBalance();
		joined.setAccountId(customer.getAccountId());
		joined.setCustomerId(customer.getCustomerId());
		joined.setPhoneNumber(customer.getPhoneNumber());
		joined.setBalance(transaction.getBalance());
		
		return joined;
	}

}
