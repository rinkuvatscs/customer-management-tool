/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.customer.management.tool.validator;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.customer.management.tool.pojo.Customer;
import com.customer.management.tool.pojo.CustomerJobDetail;

/**
 *
 * @author amittal
 */

@Component
@Scope("prototype")
public class ValidateCustomer {

	// Check the Customer fields while addind Customer.
	public void isValidCustomer(Customer customer) throws Exception {

		if (StringUtils.isEmpty(customer)) {
			throw new Exception("Customer is Empty...");
		}
		if (StringUtils.isEmpty(customer.getEmail())) {
			throw new Exception("Customer Email is Empty...");
		} else if (StringUtils.isEmpty(customer.getAddress())) {
			throw new Exception("Customer Address is Empty...");
		} else if (StringUtils.isEmpty(customer.getMobile())) {
			throw new Exception("Customer Mobile Number is Empty...");
		} else if (StringUtils.isEmpty(customer.getName())) {
			throw new Exception("Customer Name is Empty...");
		}
	}

	// Check that whether the warrenty date is before the current date or not.
	// Warrent date should be after today's date.
	public void isWarrentyDate(Date date) throws Exception {

		if (StringUtils.isEmpty(date)) {
			throw new Exception("Please specify Warrenty date...");
		}
		Calendar cal = Calendar.getInstance();
		Date todaysDate = cal.getTime();
		if (date.before(todaysDate)) {
			throw new Exception("Please specify after today's Date...");
		}
	}

	public void isRepairDetails(CustomerJobDetail reparingDetail)
			throws Exception {

		if (StringUtils.isEmpty(reparingDetail)) {
			throw new Exception("All Details are Empty");
		} else if (StringUtils.isEmpty(reparingDetail.getActualAmount())) {
			throw new Exception("Actual Amount is Empty");
		} else if (StringUtils.isEmpty(reparingDetail.getDescription())) {
			throw new Exception("Description is Empty");
		} /*else if (StringUtils.isEmpty(reparingDetail.getModel_Vehicle())) {
			throw new Exception("Model Numer is Empty");
		}*/ else if (StringUtils.isEmpty(reparingDetail.getPaidAmount())) {
			throw new Exception("Paid Amount is Empty");
		} else if (StringUtils.isEmpty(reparingDetail.getWarranty())) {
			throw new Exception("Warrenty Date is Empty");
		}
	}

	public void isValidMobileNumber(String mobile) throws Exception {

		if ("".equals(mobile) && mobile.length() == 10) {
			String regex = "^\\+?[0-9. ()-]{10,25}$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(mobile);
			if (!matcher.matches()) {
				throw new Exception("Please provide numbers only");
			}
		} else {
			throw new Exception("Please input 10 digit mobile Number");
		}
	}
}
