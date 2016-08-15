package com.customer.management.tool.constants;

public enum CMTMessageCode implements java.io.Serializable {

	ADD_CUSTOMER("addcustomer"), ADD_USER("addUser"), UPDATE_USER("updateUser"), LOGIN(
			"login"), TITLE("title"), USERNAME("username"), PASSWORD("pswd"), CONFIRM_PASSWRD(
			"confirmPswd"), ROLE("role"), SUBMIT("submitButton"), BACK("back"), NAME(
			"name"), MOBILE("mobile"), EMAIL("email"), USER_DETAIL("userDetail"), GET(
			"get"), UPDATE("update"), DELETE("delete"), USER_ID("userId"), REGISTER_DATE(
			"registerDate"), USER_ADDED("userAdded"), USER_NOT_ADDED(
			"userNotAdded"), USER_EXIST("userExist"), INAPPROPRIATE("notAppropriate"), USER_UPDATED("userUpdated");

	// String that represents a property
	private String property = null;

	/**
	 * Enum constructor
	 */
	CMTMessageCode(String property) {
		this.property = property;
	}

	/**
	 * Return the enumeration from the String
	 * 
	 * @link CMTMessageCode
	 */
	public static CMTMessageCode fromValue(String value) {
		return valueOf(value);
	}

	/**
	 * Return the value of enumeration
	 * 
	 * @return enumerationValue {@link String}
	 */
	public String getValue() {
		return property;
	}
}
