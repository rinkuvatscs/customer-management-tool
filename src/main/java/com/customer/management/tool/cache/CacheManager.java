package com.customer.management.tool.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.customer.management.tool.impl.CMTImpl;
import com.customer.management.tool.pojo.CMTCategory;

@Component
public class CacheManager {

	@Autowired
	private CMTImpl cmtImpl;
	
	public static final Map<Integer, String> categoryMap = new HashMap<>();
	
	public void insertInCategoryMap(){
		
		List<CMTCategory> response = cmtImpl.getAllCategories();
		if(!StringUtils.isEmpty(response) && !response.isEmpty()){
			for(CMTCategory cmtCategory : response){
				categoryMap.put(cmtCategory.getCategory_id(), cmtCategory.getCategory_name());
			}
		}
	}
}
