package com.customer.management.tool.pojo;

public class CMTOrderManagement {

	private int orderId;
	private int customer_id;
	private CMTOrderStatus cmtOrderStatus;
	private String description;
	private String order_date;
	private String completion_date;
	private String status;

	/**
	 * @return the orderId
	 */
	public int getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId
	 *            the orderId to set
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the customer_id
	 */
	public int getCustomer_id() {
		return customer_id;
	}

	/**
	 * @param customer_id
	 *            the customer_id to set
	 */
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the order_date
	 */
	public String getOrder_date() {
		return order_date;
	}

	/**
	 * @param order_date
	 *            the order_date to set
	 */
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	/**
	 * @return the completion_date
	 */
	public String getCompletion_date() {
		return completion_date;
	}

	/**
	 * @param completion_date
	 *            the completion_date to set
	 */
	public void setCompletion_date(String completion_date) {
		this.completion_date = completion_date;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public CMTOrderStatus getCmtOrderStatus() {
		return cmtOrderStatus;
	}

	public void setCmtOrderStatus(CMTOrderStatus cmtOrderStatus) {
		this.cmtOrderStatus = cmtOrderStatus;
	}

}
