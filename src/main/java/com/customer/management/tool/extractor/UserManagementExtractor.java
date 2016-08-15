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

import com.customer.management.tool.pojo.UserDetailHistory;

/**
 *
 * @author amittal
 */
public class UserManagementExtractor implements ResultSetExtractor<List<UserDetailHistory>> {

	@Override
	public List<UserDetailHistory> extractData(ResultSet rs) throws SQLException, DataAccessException {
		UserDetailHistory userDetailHistory;
		List<UserDetailHistory> detailList = new ArrayList<>();
		while (rs.next()) {
			userDetailHistory = new UserDetailHistory();
			userDetailHistory.setUserId(rs.getInt("userId"));
			userDetailHistory.setUsername(rs.getString("username"));
			userDetailHistory.setName(rs.getString("name"));
			userDetailHistory.setEmail(rs.getString("email"));
			userDetailHistory.setMobile(rs.getString("mobile"));
			userDetailHistory.setRegisteredDate(rs.getTimestamp("registeredDate").toString());
			userDetailHistory.setStatus(rs.getString("status"));
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			for (int x = 1; x <= columns; x++) {
				if ("customer_Id".equals(rsmd.getColumnName(x))) {
					userDetailHistory.setLastUpdated(rs.getTimestamp("lastUpdated").toString());
				} else if ("ac_type".equals(rsmd.getColumnName(x))) { // Need to Modify AC_TYPE with CMT_TYPE
					userDetailHistory.setDescription(rs.getString("description"));
				}
			}
			detailList.add(userDetailHistory);
		}
		return detailList;
	}
}
