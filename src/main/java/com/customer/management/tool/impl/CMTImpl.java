/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.customer.management.tool.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.customer.management.tool.dao.impl.CMTDaoImpl;
import com.customer.management.tool.dao.impl.CMTJobDaoImpl;
import com.customer.management.tool.pojo.CMTCategory;
import com.customer.management.tool.pojo.CMTUniqueDetail;
import com.customer.management.tool.pojo.Customer;
import com.customer.management.tool.pojo.CustomerJobDetail;
import com.customer.management.tool.validator.ValidateCustomer;

/**
 *
 * @author amittal
 */
@Component
// @Scope("prototype")
public class CMTImpl {

	@Autowired
	private CMTDaoImpl customerDaoImpl;
	@Autowired
	private ValidateCustomer validateCustomer;
	@Autowired
	private CMTJobDaoImpl cmtJobDaoImpl;

	public List<CMTUniqueDetail> getAcType() {

		return customerDaoImpl.getAcTypes();
	}

	public String addCustomer(Customer customer) throws Exception {

		return customerDaoImpl.addCustomer(customer);
	}

	public List<Customer> getCustomerInfo(Customer customer) {

		return customerDaoImpl.getCustomer(customer);
	}

	public String addRepairDetail(CustomerJobDetail customerReparingDetail) throws Exception {

		validateCustomer.isRepairDetails(customerReparingDetail);
		return customerDaoImpl.addCustomerRepairDetails(customerReparingDetail);
	}

	public Customer getCustomerID(Customer customer) {

		return customerDaoImpl.getCustomerId(customer);
	}

	public List<CustomerJobDetail> getRepairDetailByCustomerId(CustomerJobDetail jobDetail) {

		List<CustomerJobDetail> customerReparingDetails = new ArrayList<>();

		List<CustomerJobDetail> customerJobDetails = customerDaoImpl.findRepairDetailsByCustomerId(jobDetail);

		if (!StringUtils.isEmpty(customerJobDetails) && !customerJobDetails.isEmpty()) {
			Date date = new Date();
			for (CustomerJobDetail customerJobDetail : customerJobDetails) {
				jobDetail = customerJobDetail;
				if (customerJobDetail.getWarranty().compareTo(date.toString()) == 0) {
					System.out.println("Today is your last day of Warrenty");
					jobDetail.setIsWarrantyExpired("NO");
				} else if (jobDetail.getWarranty().compareTo(date.toString()) == 1) {
					customerJobDetail.setIsWarrantyExpired("NO");
				} else {
					customerJobDetail.setIsWarrantyExpired("YES");
				}
				customerReparingDetails.add(jobDetail);
			}
		} else {
			jobDetail = new CustomerJobDetail();
		}
		return customerReparingDetails;
	}

	public String addNewCategory(CMTCategory category) {

		String response = null;
		response = cmtJobDaoImpl.addCategory(category);
		return response;
	}

	public List<CMTCategory> getAllCategories() {

		return cmtJobDaoImpl.getCategories();
	}

	public String addCustomerJobDetails(CustomerJobDetail customerJobDetail) {

		String response = null;
		response = cmtJobDaoImpl.addCustomerJob(customerJobDetail);
		return response;
	}

	public List<CustomerJobDetail> getOrSearchJobList(CustomerJobDetail customerJobDetail) {

		return cmtJobDaoImpl.searchJobOfCustomer(customerJobDetail);
	}

	public List<CustomerJobDetail> searchByDate(Date startDate, Date endDate, String pending) {

		return cmtJobDaoImpl.jobByDate(startDate, endDate, pending);
	}

	public String changeOrderStatus(CustomerJobDetail customerJobDetail) {

		return cmtJobDaoImpl.orderStatusChange(customerJobDetail);
	}
}
