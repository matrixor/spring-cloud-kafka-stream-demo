package com.poslog.stream.api.model;

import lombok.Data;

@Data
public class LineItem {
	protected String itemId;
	protected int quantity;
	protected int lineitemseq;
	protected double price;
	protected String description;
	protected String taxExemptionCode;
	protected Salelinetaxes salelinetaxes;
	protected String discounts;
}
