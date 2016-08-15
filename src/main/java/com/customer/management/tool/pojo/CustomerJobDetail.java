/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.customer.management.tool.pojo;

/**
 *
 * @author amittal
 */

public class CustomerJobDetail extends Customer {

	private int jobId;
	// private int order_id;
	private String unique_Id;
	private int category_id;
	private String customer_name;
	private String description;
	private String actualAmount;
	private String paidAmount;
	private String updateDate;
	private String jobStatus;
	private String warranty;
	private String dueDate;
	private String reason;
	private String isWarrantyExpired;
	private String finalAmount;
	private String updatedDateRetun;
	private boolean startWork;
	private CMTCategory cmtCategory;
	// private CMTOrderManagement cmtOrderManagement;

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
	 * @return the unique_Id
	 */
	public String getUnique_Id() {
		return unique_Id;
	}

	/**
	 * @param unique_Id
	 *            the unique_Id to set
	 */
	public void setUnique_Id(String unique_Id) {
		this.unique_Id = unique_Id;
	}

	/**
	 * @return the actualAmount
	 */
	public String getActualAmount() {
		return actualAmount;
	}

	/**
	 * @param actualAmount
	 *            the actualAmount to set
	 */
	public void setActualAmount(String actualAmount) {
		this.actualAmount = actualAmount;
	}

	/**
	 * @return the paidAmount
	 */
	public String getPaidAmount() {
		return paidAmount;
	}

	/**
	 * @param paidAmount
	 *            the paidAmount to set
	 */
	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}

	/**
	 * @return the updateDate
	 */
	public String getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate
	 *            the updateDate to set
	 */
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return jobStatus;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.jobStatus = status;
	}

	/**
	 * @return the warranty
	 */
	public String getWarranty() {
		return warranty;
	}

	/**
	 * @param warranty
	 *            the warranty to set
	 */
	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}

	/**
	 * @return the model_Vehicle //
	 */

	public String getIsWarrantyExpired() {
		return isWarrantyExpired;
	}

	public void setIsWarrantyExpired(String isWarrantyExpired) {
		this.isWarrantyExpired = isWarrantyExpired;
	}

	public String getUpdatedDateRetun() {
		return updatedDateRetun;
	}

	public void setUpdatedDateRetun(String updatedDateRetun) {
		this.updatedDateRetun = updatedDateRetun;
	}

	public String getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(String finalAmount) {
		this.finalAmount = finalAmount;
	}

	/**
	 * @return the jobId
	 */
	public int getJobId() {
		return jobId;
	}

	/**
	 * @param jobId
	 *            the jobId to set
	 */
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	/**
	 * @return the customer_name
	 */
	public String getCustomer_name() {
		return customer_name;
	}

	/**
	 * @param customer_name
	 *            the customer_name to set
	 */
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	/**
	 * @return the category_id
	 */
	public int getCategory_id() {
		return category_id;
	}

	/**
	 * @param category_id
	 *            the category_id to set
	 */
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	/**
	 * @return the dueDate
	 */
	public String getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate
	 *            the dueDate to set
	 */
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason
	 *            the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	public CMTCategory getCmtCategory() {
		return cmtCategory;
	}

	public void setCmtCategory(CMTCategory cmtCategory) {
		this.cmtCategory = cmtCategory;
	}

	public boolean isStartWork() {
		return startWork;
	}

	public void setStartWork(boolean startWork) {
		this.startWork = startWork;
	}
}
