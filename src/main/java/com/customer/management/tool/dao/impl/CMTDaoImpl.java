/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.customer.management.tool.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.customer.management.tool.constants.CMTQueryConstant;
import com.customer.management.tool.dao.CMTDao;
import com.customer.management.tool.extractor.CMTCustomerExtractor;
import com.customer.management.tool.extractor.CMTTypesExtractor;
import com.customer.management.tool.extractor.CustomerJobDetailExtractor;
import com.customer.management.tool.pojo.CMTUniqueDetail;
import com.customer.management.tool.pojo.Customer;
import com.customer.management.tool.pojo.CustomerJobDetail;

/**
 *
 * @author amittal
 */
@Component
@Scope("prototype")
public class CMTDaoImpl implements CMTDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<CMTUniqueDetail> getAcTypes() {
		return jdbcTemplate.query(CMTQueryConstant.AC_SERVICE, new CMTTypesExtractor());
	}

	@Override
	public String addCustomer(Customer customer) throws Exception {

		String response = null;
		if (!isExist(customer)) {
			List<Object> args = new ArrayList<>();
			args.add(customer.getName());
			args.add(customer.getEmail());
			args.add(customer.getAddress());
			args.add(customer.getMobile());
			args.add(new Date());
			int result = jdbcTemplate.update(CMTQueryConstant.ADDCUSTOMER, args.toArray());
			if (result > 0) {
				response = "Customer " + customer.getName() + " successfully added";
			}
		} else {
			throw new Exception("Email or Password already Exist");
		}
		return response;
	}

	@Override
	public boolean isExist(Customer customer) {

		boolean isExist = false;
		List<String> args = new ArrayList<>();
		args.add(customer.getEmail());
		args.add(customer.getMobile());
		List<Customer> response = jdbcTemplate.query(CMTQueryConstant.IS_CUSTOMER_EXIST, new CMTCustomerExtractor(),
				args.toArray());
		if (!StringUtils.isEmpty(response) && response.size() > 0) {
			isExist = true;
		}
		return isExist;
	}

	@Override
	public List<Customer> getCustomer(Customer customer) {

		boolean isEmail = false, isMobile = false, isAddress = false;
		StringBuilder query = new StringBuilder(CMTQueryConstant.GETCUSTOMER);
		List<String> args = new ArrayList<>();
		if (!StringUtils.isEmpty(customer.getEmail())) {
			query.append(" WHERE email = ? ");
			args.add(customer.getEmail());
			isEmail = true;
		}
		if (isEmail) {
			if (!StringUtils.isEmpty(customer.getMobile())) {
				query.append(" OR mobile = ? ");
				args.add(customer.getMobile());
				isMobile = true;
			}
		} else if (!StringUtils.isEmpty(customer.getMobile())) {
			query.append(" WHERE mobile = ? ");
			args.add(customer.getMobile());
		}
		if (isMobile || isEmail) {
			if (!StringUtils.isEmpty(customer.getAddress())) {
				query.append(" OR address LIKE ? ");
				args.add("%" + customer.getAddress() + "%");
			}
		} else if (!StringUtils.isEmpty(customer.getAddress())) {
			query.append(" WHERE address LIKE ? ");
			args.add("%" + customer.getAddress() + "%");
			isAddress = true;
		}
		if (isMobile || isEmail || isAddress) {
			if (!StringUtils.isEmpty(customer.getCustomerId()) && customer.getCustomerId() != -1) {
				query.append(" OR customerId = ? ");
				args.add(String.valueOf(customer.getCustomerId()));
			}
		} else if (!StringUtils.isEmpty(customer.getCustomerId()) && customer.getCustomerId() != -1) {
			query.append(" WHERE customerId = ? ");
			args.add(String.valueOf(customer.getCustomerId()));
		}

		List<Customer> response = jdbcTemplate.query(query.toString(), new CMTCustomerExtractor(), args.toArray());

		return response;
	}

	@Override
	public String addCustomerRepairDetails(CustomerJobDetail customerReparingDetail) {

		String response = null;
		if (!StringUtils.isEmpty(customerReparingDetail)) {
			String query = "insert into customer_repairing_detail (repairId, ac_Id, customer_Id, description,"
					+ "actual_amount, paid_amount, model_vehicle_no, updated_date, warranty) "
					+ "values (0,?,?,?,?,?,?,?,?)";
			List<Object> args = new ArrayList<>();
			args.add(String.valueOf(customerReparingDetail.getJobId()));
			args.add(String.valueOf(customerReparingDetail.getCustomerId()));
			args.add(customerReparingDetail.getDescription());
			args.add(customerReparingDetail.getActualAmount());
			args.add(customerReparingDetail.getPaidAmount());
			// args.add(customerReparingDetail.getModel_Vehicle());
			args.add(customerReparingDetail.getUpdateDate());
			args.add(customerReparingDetail.getWarranty());

			int success = jdbcTemplate.update(query, args.toArray());
			if (success > 0) {
				response = "success";
			}
		}
		return response;
	}

	public Customer getCustomerId(Customer customer) {

		if (!StringUtils.isEmpty(customer) && !StringUtils.isEmpty(customer.getEmail())) {
			String query = "SELECT * FROM customer WHERE email = ? ";
			List<String> args = new ArrayList<>();
			args.add(customer.getEmail());
			List<Customer> customerList = jdbcTemplate.query(query, new CMTCustomerExtractor(), args.toArray());
			if (!StringUtils.isEmpty(customerList) && customerList.size() > 0) {
				customer.setCustomerId(customerList.get(0).getCustomerId());
			}
		}
		return customer;
	}

	@Override
	public List<CustomerJobDetail> findRepairDetailsByCustomerId(CustomerJobDetail reparingDetail) {

		String query = "select * from ( ( select * from ac_service.CUSTOMER_REPAIRING_DETAIL where customer_Id=? ) a , (select * from ac_service.CUSTOMER  where CUSTOMERID =?) b ) where a.customer_Id = b.CUSTOMERID";
		List<String> args = new ArrayList<>();
		if (!StringUtils.isEmpty(reparingDetail) && !StringUtils.isEmpty(reparingDetail.getCustomerId())) {
			args.add(String.valueOf(reparingDetail.getCustomerId()));
			args.add(String.valueOf(reparingDetail.getCustomerId()));
			System.out.println(System.currentTimeMillis());
			List<CustomerJobDetail> customerJobDetails = jdbcTemplate.query(query, new CustomerJobDetailExtractor(),
					args.toArray());
			// System.out.println(System.currentTimeMillis());
			if (!StringUtils.isEmpty(customerJobDetails) && !customerJobDetails.isEmpty()) {
				return customerJobDetails;
			}
		}
		return null;
	}

	public String updateCustomer(Customer customer) {

		String response = null;
		String query = "UPDATE CUSTOMER SET name = ? , email = ? , mobile = ? , address = ? , "
				+ "customerStatus = ? , last_updated = NOW() WHERE  customerId = ?";
		if (!StringUtils.isEmpty(customer)) {
			Object[] args = { customer.getName(), customer.getEmail(), customer.getMobile(), customer.getAddress(),
					customer.getStatus(), customer.getCustomerId() };
			int update = jdbcTemplate.update(query, args);
			if (update > 0) {
				response = "Successfully Updated";
			} else {
				response = "Unable to update it";
			}
		}
		return response;
	}

	public String deleteCustomer(String customerId) {

		String response = null;
		String query = "UPDATE CUSTOMER SET customerStatus = 'D' WHERE customerId = ? ";
		if (!StringUtils.isEmpty(customerId)) {
			Object[] args = { customerId };
			int deleted = jdbcTemplate.update(query, args);
			if (deleted > 0) {
				response = customerId + " Successfully Deleted";
			} else {
				response = "Unable to delete it";
			}
		}
		return response;
	}
}
