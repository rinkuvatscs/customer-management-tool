package com.customer.management.tool.dao;

import java.util.List;

import com.customer.management.tool.pojo.CMTCategory;
import com.customer.management.tool.pojo.CustomerJobDetail;

public interface CMTJobDao {

	public String addCategory(CMTCategory category);
	
	public List<CMTCategory> getCategories();
	
	public String addCustomerJob(CustomerJobDetail customerJobDetail);
	
	public int getLastInsertedOrderID();
	
	public List<CustomerJobDetail> searchJobOfCustomer(CustomerJobDetail customerJobDetail);

}
