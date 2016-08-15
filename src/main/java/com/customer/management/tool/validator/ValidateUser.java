/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.customer.management.tool.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.customer.management.tool.pojo.CMTLogin;
import com.customer.management.tool.pojo.UserDetail;

/**
 *
 * @author amittal
 */
@Component
@Scope("prototype")
public class ValidateUser {

	public void validateLogin(CMTLogin login) throws Exception {
		if ((StringUtils.isEmpty(login.getPassword())) || (StringUtils.isEmpty(login.getUsername()))) {
			throw new Exception("Username or Password field is empty");
		} else if (login.getRole().equals("select")) {
			throw new Exception("Please select role");
		}
	}

	public void validateUser(UserDetail userDetail) throws Exception {

		if (userDetail != null) {
			if (StringUtils.isEmpty(userDetail.getEmail())) {
				throw new Exception("Please provide any Email ID");
			} else if (StringUtils.isEmpty(userDetail.getMobile())) {
				throw new Exception("Please provide Mobile Number ");
			} else if (StringUtils.isEmpty(userDetail.getName())) {
				throw new Exception("Please confirm your Name ");
			} else if (StringUtils.isEmpty(userDetail.getUsername())) {
				throw new Exception("Please create username ");
			}
			validateEmail(userDetail);
			validateMobile(userDetail);
		} else {
			throw new Exception("Please fill User details");
		}
	}

	public void validateEmail(UserDetail userDetail) throws Exception {

		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(userDetail.getEmail());
		if (!matcher.matches()) {
			throw new Exception("Please enter valid Email Address");
		}
	}
	
	public void validateMobile(UserDetail userDetail) throws Exception{
		
		String regex  = "^[7-9][0-9]{9}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(userDetail.getMobile());
		if (!matcher.matches()) {
			throw new Exception("Please enter valid Mobile Address");
		}
	}

	public String textFieldValidation(String textField) {

		String validate = null;
		if (StringUtils.isEmpty(textField) || StringUtils.containsWhitespace(textField)) {
			validate = "Field is empty or it contains white Spaces";
		}
		return validate;
	}

	public void validateGeteUser(UserDetail detail) throws Exception {

		if (!StringUtils.isEmpty(detail)
				&& (StringUtils.isEmpty(detail.getUsername()) || detail.getUsername().equals(""))
				&& (StringUtils.isEmpty(detail.getEmail()))) {
			throw new Exception("Please provide atleast one detail");
		}
	}

	public void validateUpdate_DeleteUser(UserDetail userDetail) throws Exception {

		if (!StringUtils.isEmpty(userDetail)
				&& (!StringUtils.isEmpty(userDetail.getEmail()) || !StringUtils.isEmpty(userDetail.getMobile())
						|| !StringUtils.isEmpty(userDetail.getName()))
				|| !StringUtils.isEmpty(userDetail.getUsername())) {
		} else {
			throw new Exception("please enter values");
		}
	}
}
