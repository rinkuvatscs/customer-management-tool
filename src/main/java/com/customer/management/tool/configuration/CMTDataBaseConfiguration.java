/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.customer.management.tool.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

/**
 *
 * @author amittal
 */
@Component
public class CMTDataBaseConfiguration {

	@Value("${db.mysql.driver}")
	private String dbDriver;
	@Value("${db.mysql.url}")
	private String dbURL;
	@Value("${db.mysql.username}")
	private String dbUsername;
	@Value("${db.mysql.password}")
	private String dbPassword;

	@Bean
	public DataSource getDataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(dbDriver);
		if (!System.getProperty("user.name").equalsIgnoreCase("amittal")) {
			dataSource.setUrl("jdbc:mysql://127.0.0.1/customer_mgmt_tool");
		} else {
			dataSource.setUrl(dbURL);
		}
		dataSource.setUsername(dbUsername);
		dataSource.setPassword(dbPassword);
		return dataSource;
	}

	@Bean(name = "jdbcTemplate")
	public JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(getDataSource());
	}
}
