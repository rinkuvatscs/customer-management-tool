/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.customer.management.tool.dao;

import java.util.List;

import com.customer.management.tool.pojo.CMTLogin;
import com.customer.management.tool.pojo.UserDetailHistory;

/**
 *
 * @author amittal
 */
public interface UserManagementDao {

	public boolean authenticateUser(CMTLogin login);

	public String addUser(UserDetailHistory userDetail, CMTLogin login) throws Exception;

	public boolean isUserExist(UserDetailHistory detail);

	public List<UserDetailHistory> getUsers(UserDetailHistory detail);

	public String updateUser(UserDetailHistory detail);

	public String activateDeactivateUser(UserDetailHistory detailHistory);

	public void addUserDetailHistory(UserDetailHistory userDetailHistory);

}
