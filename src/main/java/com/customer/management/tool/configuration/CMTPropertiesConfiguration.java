package com.customer.management.tool.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.customer.management.tool.dao.impl.UserMgmtDaoImpl;
import com.customer.management.tool.ui.LoginForm;
import com.customer.management.tool.ui.admin.AddUser;
import com.customer.management.tool.ui.admin.GetUser;
import com.customer.management.tool.ui.admin.GetUsers;
import com.customer.management.tool.ui.admin.ModifyUser;
import com.customer.management.tool.ui.admin.WelcomeForm;

@Configuration
public class CMTPropertiesConfiguration {

	@Bean(name = "MessageSource")
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource obj = new ResourceBundleMessageSource();
		obj.setBasenames("bundles/Messages");
		return obj;
	}

	@Bean
	public LoginForm loginForm() {
		return new LoginForm(messageSource());
	}

	@Bean
	public WelcomeForm welcomeForm() {
		return new WelcomeForm(messageSource());
	}
	
	@Bean
	public AddUser addUser(){
		return new AddUser(messageSource());
	}
	
	@Bean
	public GetUser getUser(){
		return new GetUser(messageSource());
	}
	
	@Bean
	public GetUsers getUsers(){
		return new GetUsers(messageSource());
	}
	
	@Bean
	public ModifyUser modifyUser(){
		return new ModifyUser(messageSource());
	}
	
	@Bean
	public UserMgmtDaoImpl userMgmtDaoImpl(){
		return new UserMgmtDaoImpl(messageSource());
	}
}
