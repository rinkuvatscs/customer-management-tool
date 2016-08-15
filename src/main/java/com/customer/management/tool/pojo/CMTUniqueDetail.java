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
public class CMTUniqueDetail{

	private int unique_Id;
	private String unique_description;
	private String unique_Status;

	/**
	 * @return the unique_Id
	 */
	public int getUnique_Id() {
		return unique_Id;
	}

	/**
	 * @param unique_Id
	 *            the unique_Id to set
	 */
	public void setUnique_Id(int unique_Id) {
		this.unique_Id = unique_Id;
	}

	/**
	 * @return the unique_description
	 */
	public String getUnique_description() {
		return unique_description;
	}

	/**
	 * @param unique_description
	 *            the unique_description to set
	 */
	public void setUnique_description(String unique_description) {
		this.unique_description = unique_description;
	}

	/**
	 * @return the unique_Status
	 */
	public String getUnique_Status() {
		return unique_Status;
	}

	/**
	 * @param unique_Status
	 *            the unique_Status to set
	 */
	public void setUnique_Status(String unique_Status) {
		this.unique_Status = unique_Status;
	}
}
