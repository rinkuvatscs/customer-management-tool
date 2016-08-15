package com.customer.management.tool.extractor;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.customer.management.tool.pojo.CMTCategory;
import com.customer.management.tool.pojo.CMTOrderManagement;
import com.customer.management.tool.pojo.CMTOrderStatus;
import com.customer.management.tool.pojo.CustomerJobDetail;

public class CustomerJobDetailExtractor implements ResultSetExtractor<List<CustomerJobDetail>> {

	@Override
	public List<CustomerJobDetail> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<CustomerJobDetail> customerJobDetails = new ArrayList<CustomerJobDetail>();
		CustomerJobDetail customerJobDetail = null;
		while (rs.next()) {
			customerJobDetail = new CustomerJobDetail();
			customerJobDetail.setJobId(rs.getInt("job_id"));
			customerJobDetail.setCustomerId(rs.getInt("customer_id"));
			customerJobDetail.setCategory_id(rs.getInt("category_id"));
			customerJobDetail.setUnique_Id(rs.getString("unique_id"));
			customerJobDetail.setActualAmount(rs.getString("actual_amount"));
			customerJobDetail.setPaidAmount(rs.getString("paid_amount"));
			customerJobDetail.setDescription(rs.getString("description"));
			customerJobDetail.setUpdateDate(rs.getDate("due_date").toString());
			customerJobDetail.setDueDate(rs.getDate("due_date").toString());
			if (rs.getDate("warranty") != null) {
				customerJobDetail.setWarranty(rs.getDate("warranty").toString());
			}
			customerJobDetail.setStatus(rs.getString("status"));
			customerJobDetail.setReason(rs.getString("reason"));
			CMTCategory cmtCategory = new CMTCategory();
			CMTOrderManagement cmtOrderManagement = new CMTOrderManagement();
			CMTOrderStatus cmtOrderStatus = new CMTOrderStatus();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			for (int x = 1; x <= columns; x++) {
				if ("customerId".equals(rsmd.getColumnName(x))) {
					customerJobDetail.setCategory_id(rs.getInt("customerId"));
				} else if ("name".equals(rsmd.getColumnName(x))) {
					customerJobDetail.setName(rs.getString("name"));
				} else if ("email".equals(rsmd.getColumnName(x))) {
					customerJobDetail.setEmail(rs.getString("email"));
				} else if ("mobile".equals(rsmd.getColumnName(x))) {
					customerJobDetail.setMobile(rs.getString("mobile"));
				} else if ("address".equals(rsmd.getColumnName(x))) {
					customerJobDetail.setAddress(rs.getString("address"));
				} else if ("RegisteredDate".equals(rsmd.getColumnName(x))) {
					customerJobDetail.setRegisteredOn(rs.getString("RegisteredDate"));
				} else if ("address".equals(rsmd.getColumnName(x))) {
					customerJobDetail.setAddress(rs.getString("address"));
				} else if ("categoryId".equals(rsmd.getColumnName(x))) {
					cmtCategory.setCategory_id(rs.getInt("categoryId"));
				} else if ("category_name".equals(rsmd.getColumnName(x))) {
					cmtCategory.setCategory_name(rs.getString("category_name"));
				} else if ("category_status".equals(rsmd.getColumnName(x))) {
					cmtCategory.setStatus(rs.getString("category_status"));
				} else if ("orderId".equals(rsmd.getColumnName(x))) {
					cmtOrderManagement.setOrderId(rs.getInt("orderId"));
				} else if ("order_status".equals(rsmd.getColumnName(x))) {
					cmtOrderStatus.setOrder_status(rs.getString("order_status"));
				} else if ("order_value".equals(rsmd.getColumnName(x))) {
					cmtOrderStatus.setOrder_value(rs.getString("order_value"));
				}
			}
			cmtOrderManagement.setCmtOrderStatus(cmtOrderStatus);
			customerJobDetail.setCmtOrderManagement(cmtOrderManagement);
			customerJobDetail.setCmtCategory(cmtCategory);
			customerJobDetails.add(customerJobDetail);
		}
		return customerJobDetails;
	}
}
