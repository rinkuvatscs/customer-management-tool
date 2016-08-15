package com.customer.management.tool.constants;

public enum UserManagementCode {

	ACTIVATE("A"), DEACTIVATE("D"), DELETE("DE");

	private String prperty = null;

	public String getPrperty() {
		return prperty;
	}

	private UserManagementCode(String prperty) {
		this.prperty = prperty;
	}

	public static UserManagementCode fromValue(String value) {
		return valueOf(value);
	}

}
