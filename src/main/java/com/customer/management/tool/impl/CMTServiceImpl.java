package com.customer.management.tool.impl;

import javax.swing.JFrame;

import org.springframework.beans.factory.annotation.Autowired;

public class CMTServiceImpl extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	protected CMTUserManagementImpl acServiceUserImpl;
	@Autowired
	protected CMTImpl cmtImpl;
	@Autowired
	protected CMTReportGeneratorImpl acReportGeneratorImpl;
//	public JFrame frame = new JFrame();
	

}
