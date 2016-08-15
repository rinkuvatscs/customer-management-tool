/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.customer.management.tool.impl;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.customer.management.tool.CMTDevelopmentTool;
import com.customer.management.tool.dao.impl.UserManagementDaoImpl;
import com.customer.management.tool.dao.impl.UserMgmtDaoImpl;
import com.customer.management.tool.pojo.CMTLogin;
import com.customer.management.tool.pojo.UserDetailHistory;
import com.customer.management.tool.validator.ValidateUser;

/**
 *
 * @author amittal
 */
@Component
@Scope("prototype")
public class CMTUserManagementImpl {

	@Autowired
	private UserManagementDaoImpl userDaoImpl;
	@Autowired
	private ValidateUser validation;
	@Autowired
	UserMgmtDaoImpl userMgmtDaoImpl;

	@Value("${userManagementDaoImplEnable}")
	String userManagementDaoImplEnable;

	public String login(CMTLogin login) throws Exception {

		String response = null;
		if (!CMTDevelopmentTool.isDevelopmentMode) {
			validation.validateLogin(login);
			if (StringUtils.isEmpty(response)) {
				if (userDaoImpl.authenticateUser(login)) {
					response = "Successfully Logged in";
					JOptionPane.showMessageDialog(new JFrame(), response, response, JOptionPane.INFORMATION_MESSAGE);
					// loginForm.dispose();
					// welcomeForm.setVisible(true);
				}
			} else {
				JOptionPane.showMessageDialog(new JFrame(), response, response, JOptionPane.ERROR_MESSAGE);
			}
		} else {
			// loginForm.dispose();
			// welcomeForm.setVisible(true);
		}
		return response;
	}

	public String validateField(String field) {

		String data = null;
		String response = validation.textFieldValidation(field);
		if (response != null) {
			JOptionPane.showMessageDialog(new JFrame(), response, response, JOptionPane.ERROR_MESSAGE);
			data = "Success";
		}
		return data;
	}

	public String addUser(CMTLogin login, UserDetailHistory userDetail) throws Exception {
		String response = null;
		validation.validateLogin(login);
		validation.validateUser(userDetail);

		if (userManagementDaoImplEnable.equalsIgnoreCase("true")) {
			response = userMgmtDaoImpl.addUser(userDetail, login);
		} else {
			response = userDaoImpl.addUser(userDetail, login);
		}
		if (response.contains("Successfully")) {
			userDaoImpl.addUserDetailHistory(userDetail);
		} else {
			System.out.println("It does not contains anything in addUser");
		}
		return response;
	}

	public String activateUser(UserDetailHistory userDetailHistory) {

		String response = userDaoImpl.activateDeactivateUser(userDetailHistory);
		if (response.toLowerCase().contains("successfully")) {
			userDaoImpl.addUserDetailHistory(userDetailHistory);
		} else {
			System.out.println("It does not contains anything in activateUser ");
		}
		return response;
	}

	public UserDetailHistory getUserData(UserDetailHistory detail) throws Exception {

		validation.validateGeteUser(detail);
		List<UserDetailHistory> response = userMgmtDaoImpl.getUsers(detail);
		if (!StringUtils.isEmpty(response) && response.size() > 0) {
			return response.get(0);
		}
		throw new Exception("User is deactive, please activate it to update");
	}

	public String updateUserData(UserDetailHistory detail) throws Exception {

		validation.validateUpdate_DeleteUser(detail);
		String response = userMgmtDaoImpl.updateUser(detail);
		if (response.contains("Successfully")) {
			userDaoImpl.addUserDetailHistory(detail);
		}
		return response;
	}

	public String deleteUserData(UserDetailHistory detail) throws Exception {

		validation.validateUpdate_DeleteUser(detail);
		String response = userDaoImpl.activateDeactivateUser(detail);
		if (response.contains("Successfully")) {
			userDaoImpl.addUserDetailHistory(detail);
		}
		return response;
	}

	public String activateDeactivateUser(UserDetailHistory detailHistory) {

		String response = null;
		response = userMgmtDaoImpl.userManagement(detailHistory);
		return response;
	}
	
	public List<UserDetailHistory> getUser(UserDetailHistory detailHistory){
		
		return userMgmtDaoImpl.getUsers(detailHistory);
	}
}
