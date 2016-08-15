package com.customer.management.tool.constants;

public enum CMTOrderStatusCode {

	PENDING("P"), COMPLETED("C"), REOPEN("R");

	private String prperty = null;

	public String getPrperty() {
		return prperty;
	}

	private CMTOrderStatusCode(String prperty) {
		this.prperty = prperty;
	}

	public static CMTOrderStatusCode fromValue(String value) {
		return valueOf(value);
	}
}
