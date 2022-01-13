package com.poslog.stream.api.model;

import lombok.Data;

@Data
public class Recipient {
	protected String to;
	protected Attributes attributes;
}
