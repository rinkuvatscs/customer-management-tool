/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.customer.management.tool.extractor;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.customer.management.tool.pojo.CMTOrderManagement;
import com.customer.management.tool.pojo.CMTOrderStatus;
import com.customer.management.tool.pojo.Customer;

/**
 *
 * @author Aviral
 */
public class CMTCustomerExtractor implements ResultSetExtractor<List<Customer>> {

	List<Customer> customerList = new ArrayList<>();

	@Override
	public List<Customer> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Customer customer;
		while (rs.next()) {
			customer = new Customer();
			customer.setCustomerId(rs.getInt("customerId"));
			customer.setName(rs.getString("name"));
			customer.setEmail(rs.getString("email"));
			customer.setAddress(rs.getString("address"));
			customer.setMobile(rs.getString("mobile"));
			customer.setRegisteredOn(rs.getTimestamp("RegisteredDate").toString());
			customer.setStatus(rs.getString("customerStatus"));
			customer.setLastUpdated(rs.getString("last_updated"));
			CMTOrderManagement cmtOrderManagement = new CMTOrderManagement();
			CMTOrderStatus cmtOrderStatus = new CMTOrderStatus();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			for (int x = 1; x <= columns; x++) {
				if ("orderId".equals(rsmd.getColumnName(x))) {
					cmtOrderManagement.setOrderId(rs.getInt("orderId"));
				} else if ("order_status".equals(rsmd.getColumnName(x))) {
					cmtOrderStatus.setOrder_status(rs.getString("order_status"));
				} else if ("order_value".equals(rsmd.getColumnName(x))) {
					cmtOrderStatus.setOrder_value(rs.getString("order_value"));
				}
			}
			cmtOrderManagement.setCmtOrderStatus(cmtOrderStatus);
			customer.setCmtOrderManagement(cmtOrderManagement);
			customerList.add(customer);
		}
		return customerList;
	}

}
