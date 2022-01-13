package com.poslog.stream.api.model;

import lombok.Data;

@Data
public class Tender {
	protected String tenderType;
	protected double tenderAmount;
	protected int tenderSeq;
	protected double tenderChange;
	protected String tenderId;
	protected String maskedCardNumber;
	protected String tenderDisplayName;
}
