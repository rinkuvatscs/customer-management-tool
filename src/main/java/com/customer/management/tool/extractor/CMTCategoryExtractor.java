package com.customer.management.tool.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.customer.management.tool.pojo.CMTCategory;

public class CMTCategoryExtractor implements
		ResultSetExtractor<List<CMTCategory>> {

	@Override
	public List<CMTCategory> extractData(ResultSet rs) throws SQLException,
			DataAccessException {

		List<CMTCategory> categories = new ArrayList<CMTCategory>();
		CMTCategory category = new CMTCategory();
		while (rs.next()) {
			category = new CMTCategory();
			category.setCategory_id(rs.getInt("categoryId"));
			category.setCategory_name(rs.getString("category_name"));
			category.setStatus(rs.getString("category_status"));
			categories.add(category);
		}
		return categories;
	}

}
