/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.customer.management.tool.constants;

/**
 *
 * @author amittal
 */
public class CMTQueryConstant {

	public static String AUTHENTICATE_USER_CREDENTIALS = "SELECT * FROM LOGIN WHERE username = ? AND password = ? AND role = ?";

	public static String INSERT_IN_LOGIN = " INSERT INTO login (username,password,role) VALUES (?,?,?)";
	
	public static String GET_LOGIN_DETAIL = "SELECT * FROM login WHERE username = ? ";

	public static String INSERT_USERDETAIL = "INSERT INTO userdetail (userId,name,email,mobile,username,registeredDate) values (0,?,?,?,?,NOW())";

	public static String GET_USERDETAIL = " SELECT * FROM userdetail WHERE status = ? ";

	public static String IS_USERNAME_EXIST = "SELECT * from userdetail where username = ? ";

	public static String UPDATE_USER = " UPDATE userdetail SET name = ?, email = ?, mobile = ? where userid = ?";
	
	public static String IS_USER_ACTIVE = " SELECT * FROM userdetail WHERE username = ? ";

	public static String DELETE_USER = "UPDATE userdetail SET status = ? ";
	
	public static String ACTIVATE_USER = "UPDATE userdetail SET status = ? ";

	public static String INSERT_USERDETAILHISTORY = "INSERT INTO user_detail_history (Id,userId,name,username,email,mobile,registeredDate,"
			+ "description,lastUpdated, status) values (0,?,?,?,?,?,?,?,NOW(),?)";

	public static String AC_SERVICE = "SELECT * FROM ac_type";

	public static String ADDCUSTOMER = "INSERT INTO customer (customerId,name,email,address,mobile,RegisteredDate,last_updated) "
			+ "VALUES (0,?,?,?,?,NOW(),?)";

	public static String IS_CUSTOMER_EXIST = "SELECT * FROM customer WHERE email = ? OR mobile = ?";

	public static String GETCUSTOMER = "SELECT * FROM customer ";
}
