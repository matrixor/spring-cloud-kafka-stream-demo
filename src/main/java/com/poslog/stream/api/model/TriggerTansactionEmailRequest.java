package com.poslog.stream.api.model;

import lombok.Data;

@Data
public class TriggerTansactionEmailRequest {
	protected String sourceSystem;
	protected String businessUnit;
	protected String matchType;
	protected String matchId;
	protected String campaignName;
	protected Recipient recipient;
}
