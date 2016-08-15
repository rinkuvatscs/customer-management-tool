/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.customer.management.tool.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.customer.management.tool.pojo.CMTLogin;

/**
 *
 * @author amittal
 */
public class CMTLoginExtractor implements ResultSetExtractor<CMTLogin> {

    @Override
    public CMTLogin extractData(ResultSet rs) throws SQLException, DataAccessException {
        CMTLogin login =null ;
        if (rs.next()) {
            login = new CMTLogin(rs.getString("username"),rs.getString("password"),rs.getString("role"));
        }
        return login;
    }
}
