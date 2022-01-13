package com.poslog.stream.api.model;

import lombok.Data;

@Data
public class TaxLine {
	protected int taxLineSeq;
	protected double taxAmount;
	protected String taxLabel;
}
