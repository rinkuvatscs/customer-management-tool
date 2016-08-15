package com.customer.management.tool.extractor;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class GetLastInsertedIDExtractor implements ResultSetExtractor<Integer> {

	@Override
	public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {

		int lastId = 0;
		if (rs.next()) {
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			for (int x = 1; x <= columns; x++) {
				if ("orderId".equals(rsmd.getColumnName(x))) {
					lastId = rs.getInt("orderId");
				} else if ("categoryId".equals(rsmd.getColumnName(x))) {
					lastId = rs.getInt("categoryId");
				}
			}
		}
		return lastId;
	}

}
