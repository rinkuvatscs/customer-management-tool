package com.customer.management.tool.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.customer.management.tool.constants.CMTMessageCode;
import com.customer.management.tool.constants.CMTQueryConstant;
import com.customer.management.tool.constants.UserManagementCode;
import com.customer.management.tool.dao.UserManagementDao;
import com.customer.management.tool.extractor.UserManagementExtractor;
import com.customer.management.tool.pojo.CMTLogin;
import com.customer.management.tool.pojo.UserDetailHistory;

@Component
public class UserMgmtDaoImpl implements UserManagementDao {

	static Logger LOG = Logger.getLogger(UserManagementDaoImpl.class.getName());

	@Autowired
	private JdbcTemplate jdbcTemplate;
/*	@Autowired
	private Cipher Cipher;*/

	private ResourceBundleMessageSource messageSource;

	/*
	 * public ResourceBundleMessageSource getResourceBundleMessageSource() {
	 * return resourceBundleMessageSource; }
	 * 
	 * public void setResourceBundleMessageSource( ResourceBundleMessageSource
	 * resourceBundleMessageSource) { this.resourceBundleMessageSource =
	 * resourceBundleMessageSource; }
	 */

	public UserMgmtDaoImpl() {
	}

	public UserMgmtDaoImpl(ResourceBundleMessageSource messageSource) {
		// super();
		this.messageSource = messageSource;
	}

	@Override
	public boolean authenticateUser(CMTLogin login) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String addUser(UserDetailHistory userDetail, CMTLogin login)
			throws Exception {

		String response = null;
		if (!isUserExist(userDetail)) {
			List<String> args = null;
			args = new ArrayList<>();
			args.add(login.getUsername());
			args.add(login.getPassword());
			args.add(login.getRole());
			int success = jdbcTemplate.update(CMTQueryConstant.INSERT_IN_LOGIN,
					args.toArray());
			if (success > 0) {
				args = new ArrayList<>();
				args.add(userDetail.getName());
				args.add(userDetail.getEmail());
				args.add(userDetail.getMobile());
				args.add(userDetail.getUsername());
				int temp = jdbcTemplate.update(
						CMTQueryConstant.INSERT_USERDETAIL, args.toArray());
				if (temp > 0) {
					response = messageSource.getMessage(
							CMTMessageCode.USER_ADDED.getValue(), null,
							Locale.getDefault());
				} else {
					response = messageSource.getMessage(
							CMTMessageCode.USER_NOT_ADDED.getValue(), null,
							Locale.getDefault());
				}
			} else {
				response = "Unable to insert in CMTLogin";
			}
		} else {
			response = messageSource.getMessage(
					CMTMessageCode.USER_EXIST.getValue(), null,
					Locale.getDefault());
		}
		LOG.info(response);
		return response;
	}

	// isDetails Exist.
	@Override
	public boolean isUserExist(UserDetailHistory detail) {

		boolean isExist = false;
		if (!StringUtils.isEmpty(detail)) {
			if (!StringUtils.isEmpty(detail.getUsername())) {
				Object[] args = { detail.getUsername() };
				List<UserDetailHistory> detailHistories = jdbcTemplate.query(
						"Select * from userdetail where username = ? ",
						new UserManagementExtractor(), args);
				if (!StringUtils.isEmpty(detailHistories)
						&& !detailHistories.isEmpty()) {
					return true;
				}
			}
			if (!StringUtils.isEmpty(detail.getEmail())) {
				if (isEmailExist(detail.getEmail())) {
					return true;
				}
			}
			if (!StringUtils.isEmpty(detail.getMobile())) {
				if (isMobileExist(detail.getMobile())) {
					return true;
				}
			}
		}

		return isExist;
	}

	private boolean isEmailExist(String email) {

		boolean isExist = false;
		if (!StringUtils.isEmpty(email)) {
			Object[] args = { email };
			List<UserDetailHistory> detailHistories = jdbcTemplate.query(
					"Select * from userdetail where email = ? ",
					new UserManagementExtractor(), args);
			if (!StringUtils.isEmpty(detailHistories)
					&& !detailHistories.isEmpty()) {
				isExist = true;
			}
		}
		return isExist;
	}

	private boolean isMobileExist(String mobile) {

		boolean isExist = false;
		if (!StringUtils.isEmpty(mobile)) {
			Object[] args = { mobile };
			List<UserDetailHistory> detailHistories = jdbcTemplate.query(
					"Select * from userdetail where mobile = ? ",
					new UserManagementExtractor(), args);
			if (!StringUtils.isEmpty(detailHistories)
					&& !detailHistories.isEmpty()) {
				isExist = true;
			}
		}
		return isExist;
	}

	@Override
	public List<UserDetailHistory> getUsers(UserDetailHistory detail) {

		List<UserDetailHistory> userDetail = null;
		if (!StringUtils.isEmpty(detail)) {
			StringBuilder query = new StringBuilder(
					CMTQueryConstant.GET_USERDETAIL);
			List<String> args = new ArrayList<>();
			if (detail.getStatus() != null
					&& (detail.getStatus().equalsIgnoreCase(
							UserManagementCode.ACTIVATE.getPrperty()) || detail
							.getStatus().equalsIgnoreCase(
									UserManagementCode.DEACTIVATE.getPrperty()))) {
				args.add(detail.getStatus());
			} else {
				args.add(UserManagementCode.ACTIVATE.getPrperty());
			}
			if (org.apache.commons.lang3.StringUtils.isNotEmpty(detail
					.getUsername())) {
				query.append(" AND USERNAME = ? ");
				args.add(detail.getUsername());
			} else if (org.apache.commons.lang3.StringUtils.isNotEmpty(detail
					.getEmail())) {
				query.append(" AND EMAIL = ? ");
				args.add(detail.getEmail());
			} else if (org.apache.commons.lang3.StringUtils.isNotEmpty(detail
					.getMobile())) {
				query.append(" AND MOBILE = ? ");
				args.add(detail.getMobile());
			}
			userDetail = jdbcTemplate.query(query.toString(),
					new UserManagementExtractor(), args.toArray());
		}
		return userDetail;
	}

	@Override
	public String updateUser(UserDetailHistory detail) {

		String response = null;
		if (!StringUtils.isEmpty(detail)) {
			if (org.apache.commons.lang3.StringUtils.isNotEmpty(detail
					.getUsername())
					|| org.apache.commons.lang3.StringUtils.isNotEmpty(detail
							.getEmail())
					|| org.apache.commons.lang3.StringUtils.isNotEmpty(detail
							.getMobile())) {
				List<UserDetailHistory> user = getUsers(detail);
				if (!StringUtils.isEmpty(user) && !user.isEmpty()
						&& user.get(0).getStatus() != null) {
					if (user.get(0)
							.getStatus()
							.equalsIgnoreCase(
									UserManagementCode.ACTIVATE.getPrperty())) {
						StringBuilder query = new StringBuilder(
								"SELECT * FROM USERDETAIL WHERE USERNAME != ? ");
						List<String> args = new ArrayList<>();
						args.add(user.get(0).getUsername());
						if (!StringUtils.isEmpty(detail.getEmail())
								&& !StringUtils.isEmpty(detail.getMobile())) {
							query.append("AND  (email = ? or  mobile = ?)");
							args.add(detail.getEmail());
							args.add(detail.getMobile());
						} else if (!StringUtils.isEmpty(detail.getEmail())
								&& StringUtils.isEmpty(detail.getMobile())) {
							query.append(" AND email = ? ");
							args.add(detail.getEmail());
						} else if (StringUtils.isEmpty(detail.getEmail())
								&& !StringUtils.isEmpty(detail.getMobile())) {
							query.append(" AND mobile = ? ");
							args.add(detail.getMobile());
						} else {
							return messageSource.getMessage(
									CMTMessageCode.INAPPROPRIATE.getValue(),
									null, Locale.getDefault());
						}
						List<UserDetailHistory> users = jdbcTemplate.query(
								query.toString(),
								new UserManagementExtractor(), args.toArray());
						if (users.isEmpty()) {
							Object[] arg = { detail.getName(),
									detail.getEmail(), detail.getMobile(),
									String.valueOf(detail.getUserId()) };
							if (jdbcTemplate.update(
									CMTQueryConstant.UPDATE_USER, arg) > 0) {
								response = messageSource.getMessage(
										CMTMessageCode.USER_UPDATED.getValue(),
										null, Locale.getDefault());
							}
						} else {
							response = "Email or Mobile already Registered with us.";
						}
					} else
						response = "User is deactive, please activate to update.";
				}
			}
		}
		return response;
	}

	@Override
	public String activateDeactivateUser(UserDetailHistory detailHistory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUserDetailHistory(UserDetailHistory userDetailHistory) {

		if (!StringUtils.isEmpty(userDetailHistory)) {
			List<UserDetailHistory> history = getUsers(userDetailHistory);
			if (!StringUtils.isEmpty(history) && history.size() > 0) {
				userDetailHistory.setEmail(history.get(0).getEmail());
				userDetailHistory.setMobile(history.get(0).getMobile());
				userDetailHistory.setName(history.get(0).getName());
				userDetailHistory.setRegisteredDate(history.get(0)
						.getRegisteredDate());
				userDetailHistory.setUsername(history.get(0).getUsername());
				userDetailHistory.setUserId(history.get(0).getUserId());
				userDetailHistory.setStatus(history.get(0).getStatus());
				if ("add".equalsIgnoreCase(userDetailHistory.getDescription())) {
					commonsAddUserDetailHistory("User Successfully Added",
							userDetailHistory);
				} else if ("update".equalsIgnoreCase(userDetailHistory
						.getDescription())) {
					commonsAddUserDetailHistory("User Successfully Updated",
							userDetailHistory);
				} else if ("delete".equalsIgnoreCase(userDetailHistory
						.getDescription())) {
					commonsAddUserDetailHistory("User Successfully deleted",
							userDetailHistory);
				} else if ("active".equalsIgnoreCase(userDetailHistory
						.getDescription())) {
					commonsAddUserDetailHistory("User Successfully Activated",
							userDetailHistory);
				} else if ("deactive".equalsIgnoreCase(userDetailHistory
						.getDescription())) {
					commonsAddUserDetailHistory(
							"User Successfully Deactivated", userDetailHistory);
				}
			}
		}

	}

	public String userManagement(UserDetailHistory userDetailHistory) {

		String response = null;
		StringBuffer query = new StringBuffer(
				"UPDATE userdetail SET status = ? ");
		List<String> args = new ArrayList<>();
		args.add(userDetailHistory.getStatus());
		if (!StringUtils.isEmpty(userDetailHistory)) {
			if (org.apache.commons.lang3.StringUtils
					.isNotEmpty(userDetailHistory.getUsername())) {
				query.append(" WHERE USERNAME = ? ");
				args.add(userDetailHistory.getUsername());
			} else if (org.apache.commons.lang3.StringUtils
					.isNotEmpty(userDetailHistory.getEmail())) {
				query.append(" WHERE EMAIL = ? ");
				args.add(userDetailHistory.getEmail());
			} else if (org.apache.commons.lang3.StringUtils
					.isNotEmpty(userDetailHistory.getMobile())) {
				query.append(" WHERE MOBILE = ? ");
				args.add(userDetailHistory.getMobile());
			}
			int executed = jdbcTemplate
					.update(query.toString(), args.toArray());
			if (executed > 0) {
				if (org.apache.commons.lang3.StringUtils
						.isNotEmpty(userDetailHistory.getUsername())
						&& org.apache.commons.lang3.StringUtils
								.isNotEmpty(userDetailHistory.getStatus())) {
					response = loginStatusChange(
							userDetailHistory.getUsername(),
							userDetailHistory.getStatus());
				} else {
					List<UserDetailHistory> userDetails = getUsers(userDetailHistory);
					if (StringUtils.isEmpty(userDetails)
							&& !userDetails.isEmpty()) {
						if (org.apache.commons.lang3.StringUtils
								.isNotEmpty(userDetails.get(0).getUsername())
								&& org.apache.commons.lang3.StringUtils
										.isNotEmpty(userDetails.get(0)
												.getStatus())) {
							response = loginStatusChange(userDetails.get(0)
									.getUsername(), userDetails.get(0)
									.getStatus());
						}
						if (response != null) {
							if (userDetailHistory.getStatus().equalsIgnoreCase(
									"A")) {
								response = "User Activated Successfully";
							} else if (userDetailHistory.getStatus()
									.equalsIgnoreCase("D")) {
								response = "User Deactivated Successfully";
							} else {
								response = "User Deleted Successfully";
							}
						}
					}
				}
			} else {
				response = "Please provide valid username to activate User";
			}
		} else {
			response = "User status = " + userDetailHistory.getStatus()
					+ " not able to change, try again later";
		}

		return response;
	}

	public String deactivateUser(String username) {

		String response;
		if (!StringUtils.isEmpty(username)) {
			Object[] args = { username };
			int executed = jdbcTemplate.update(
					"UPDATE userdetail set status = 'd' WHERE username = ? ",
					args);
			if (executed > 0) {
				response = "User Deactivated Successfully";
			} else {
				response = "User remains Active, try again later";
			}
		} else {
			response = "Please provide valid username to deactivate User";
		}
		return response;
	}

	public String loginStatusChange(String username, String status) {

		String response = null;
		if (org.apache.commons.lang3.StringUtils.isNotEmpty(username)
				&& org.apache.commons.lang3.StringUtils.isNotEmpty(status)) {
			Object[] args = { status, username };
			int executed = jdbcTemplate.update(
					"UPDATE LOGIN SET STATUS = ? WHERE USERNAME = ? ", args);
			if (executed > 0) {
				response = "Successfully updated";
			} else {
				response = "Not able to update, try again later";
			}
		} else {
			response = "Fields are empty";
		}
		return response;
	}

	private void commonsAddUserDetailHistory(String description,
			UserDetailHistory userDetailHistory) {

		Object[] args = { userDetailHistory.getUserId(),
				userDetailHistory.getName(), userDetailHistory.getUsername(),
				userDetailHistory.getEmail(), userDetailHistory.getMobile(),
				userDetailHistory.getRegisteredDate(), description,
				userDetailHistory.getStatus() };
		int executed = jdbcTemplate.update(
				CMTQueryConstant.INSERT_USERDETAILHISTORY, args);
		if (executed > 0) {
			LOG.info("Successfully Added in History");
		} else {
			LOG.error("Unable to Add History");
		}
	}
}
