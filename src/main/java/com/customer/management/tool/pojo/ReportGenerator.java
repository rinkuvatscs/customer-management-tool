package com.customer.management.tool.pojo;

public class ReportGenerator {

	private String customerId;
	private String startDate;
	private String endDate;
	private String custName;
	private String amount;
	private String visitDate;
	private String year;

	public ReportGenerator() {
		super();
	}

	public ReportGenerator(String customerId, String startDate, String endDate, String custName, String amount,
			String visitDate, String year) {
		super();
		this.customerId = customerId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.custName = custName;
		this.amount = amount;
		this.visitDate = visitDate;
		this.year = year;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}
