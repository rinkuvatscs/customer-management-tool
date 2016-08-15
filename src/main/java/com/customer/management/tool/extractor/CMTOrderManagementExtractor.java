package com.customer.management.tool.extractor;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.customer.management.tool.pojo.CMTOrderManagement;
import com.customer.management.tool.pojo.CMTOrderStatus;

public class CMTOrderManagementExtractor implements ResultSetExtractor<CMTOrderManagement> {

	@Override
	public CMTOrderManagement extractData(ResultSet rs) throws SQLException, DataAccessException {
		CMTOrderManagement cmtOrderManagement = null;
		if (rs.next()) {
			cmtOrderManagement = new CMTOrderManagement();
			cmtOrderManagement.setCustomer_id(rs.getInt("customer_id"));
			cmtOrderManagement.setOrderId(rs.getInt("orderId"));
			// cmtOrderManagement.setOrder_status(rs.getString("order_status"));
			cmtOrderManagement.setDescription(rs.getString("order_description"));
			cmtOrderManagement.setOrder_date(rs.getTimestamp("order_date").toString());
			cmtOrderManagement.setCompletion_date(rs.getTimestamp("order_completion").toString());
			cmtOrderManagement.setStatus(rs.getString("status"));
			CMTOrderStatus cmtOrderStatus = new CMTOrderStatus();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			for (int x = 1; x <= columns; x++) {
				if ("order_status".equals(rsmd.getColumnName(x))) {
					cmtOrderStatus.setOrder_status(rs.getString("order_status"));
				} else if ("order_value".equals(rsmd.getColumnName(x))) {
					cmtOrderStatus.setOrder_value(rs.getString("order_value"));
				}
			}
			cmtOrderManagement.setCmtOrderStatus(cmtOrderStatus);
		}
		return cmtOrderManagement;
	}

}
