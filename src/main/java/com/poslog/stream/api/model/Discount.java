package com.poslog.stream.api.model;

import java.util.ArrayList;

import lombok.Data;

@Data
public class Discount {
	protected String discountCode;
	protected int discountCodeSeq;
	protected String discountAmount;
}
