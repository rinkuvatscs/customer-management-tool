package com.customer.management.tool.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.customer.management.tool.pojo.CMTProduct;

public class CMTProductExtractor implements
		ResultSetExtractor<List<CMTProduct>> {

	@Override
	public List<CMTProduct> extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		List<CMTProduct> cmtProducts = new ArrayList<CMTProduct>();
		CMTProduct cmtProduct = null;
		while (rs.next()) {
			cmtProduct = new CMTProduct();
			cmtProduct.setProductId(rs.getInt("productId"));
			cmtProduct.setProductName(rs.getString("product_name"));
			cmtProduct.setProductDescription(rs.getString("product_description"));
			cmtProduct.setStatus(rs.getString("status"));
			cmtProducts.add(cmtProduct);
		}
		return cmtProducts;
	}

}
