package com.customer.management.tool.pojo;

public class UserDetailHistory extends UserDetail {

	private String lastUpdated;
	private String description;
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserDetailHistory(int userId, String username, String name, String email, String mobile,
			String registeredDate, String lastUpdated, String description) {
		super(userId, username, name, email, mobile, registeredDate);
		this.lastUpdated = lastUpdated;
		this.description = description;
	}

	public UserDetailHistory(int userId, String username, String name, String email, String mobile,
			String registeredDate, String lastUpdated, String description, String status) {
		super(userId, username, name, email, mobile, registeredDate);
		this.lastUpdated = lastUpdated;
		this.description = description;
		this.status = status;
	}

	public UserDetailHistory() {
		super();
	}

}
