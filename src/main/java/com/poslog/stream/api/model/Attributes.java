package com.poslog.stream.api.model;

import lombok.Data;

@Data
public class Attributes {
	protected int storeNumber;
	protected String businessDate;
	protected int workstationId;
	protected int transactionId;
	protected Payload payload;
}
