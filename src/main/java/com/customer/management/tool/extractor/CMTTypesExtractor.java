/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.customer.management.tool.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.customer.management.tool.pojo.CMTUniqueDetail;

/**
 *
 * @author amittal
 */
public class CMTTypesExtractor implements ResultSetExtractor<List<CMTUniqueDetail>> {

    @Override
    public List<CMTUniqueDetail> extractData(ResultSet rs) throws SQLException, DataAccessException {

        List<CMTUniqueDetail> cmtUniqueDetails = new ArrayList<>();
        CMTUniqueDetail cmtUniqueDetail;
        while (rs.next()) {
            cmtUniqueDetail = new CMTUniqueDetail();
            cmtUniqueDetail.setUnique_Id(rs.getInt("unique_Id"));
            cmtUniqueDetail.setUnique_description(rs.getString("unique_description"));
            cmtUniqueDetail.setUnique_Status(rs.getString("status"));
            cmtUniqueDetails.add(cmtUniqueDetail);
        }
        return cmtUniqueDetails;
    }

}
