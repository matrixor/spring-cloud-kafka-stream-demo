package com.poslog.stream.api.model;

import java.util.List;

import lombok.Data;

@Data
public class Payload {
	LineItems lineItems;
	TaxLines taxLines;
	Tenders tenders;
	protected double subtotal;
	protected double tax;
	protected double totalDiscount;
	protected String taxExempt;
	protected double exemptTaxAmount;
	protected double total;
	protected String transactionNumber;
	protected String date;
	protected String time;
	protected String retailStoreId;
	protected String tillId;
	protected String operatorId;
	protected String countryCode;
	protected String stateCode;
	protected String cityCode;
	//protected String storeCode;
	protected String storeName;
	protected String storeType;
	//protected String barcode;
}
