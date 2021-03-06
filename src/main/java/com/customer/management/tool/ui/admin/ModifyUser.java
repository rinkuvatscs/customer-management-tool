/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.customer.management.tool.ui.admin;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import com.customer.management.tool.constants.CMTMessageCode;
import com.customer.management.tool.impl.CMTGoBackImpl;
import com.customer.management.tool.impl.CMTServiceImpl;
import com.customer.management.tool.pojo.UserDetail;
import com.customer.management.tool.pojo.UserDetailHistory;

/**
 *
 * @author amittal
 */
@Component
@Scope("prototype")
public class ModifyUser extends CMTServiceImpl implements Serializable {

	@Autowired
	private CMTGoBackImpl acServiceBackImpl;
	private static final long serialVersionUID = 1L;

	public ModifyUser(ResourceBundleMessageSource resourceBundleMessageSource) {
		initComponents(resourceBundleMessageSource);
	}

	public void setValues(UserDetail detail) {

		userid.setText(String.valueOf(detail.getUserId()));
		username.setText(detail.getUsername());
		email.setText(detail.getEmail());
		name.setText(detail.getName());
		mobile.setText(detail.getMobile());
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 * 
	 * @param messageSource
	 */
	private void initComponents(ResourceBundleMessageSource messageSource) {

		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		userid = new javax.swing.JLabel();
		username = new javax.swing.JLabel();
		name = new javax.swing.JTextField();
		email = new javax.swing.JTextField();
		mobile = new javax.swing.JTextField();
		update = new javax.swing.JButton();
		back = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		jLabel1.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
		jLabel1.setText(messageSource.getMessage(CMTMessageCode.UPDATE_USER.getValue(), null, Locale.getDefault()));

		jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
		jLabel2.setText(messageSource.getMessage(CMTMessageCode.USER_ID.getValue(), null, Locale.getDefault()));

		jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
		jLabel3.setText(messageSource.getMessage(CMTMessageCode.NAME.getValue(), null, Locale.getDefault()));

		jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
		jLabel4.setText(messageSource.getMessage(CMTMessageCode.USERNAME.getValue(), null, Locale.getDefault()));

		jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
		jLabel5.setText(messageSource.getMessage(CMTMessageCode.EMAIL.getValue(), null, Locale.getDefault()));

		jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
		jLabel6.setText(messageSource.getMessage(CMTMessageCode.MOBILE.getValue(), null, Locale.getDefault()));

		update.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
		update.setText(messageSource.getMessage(CMTMessageCode.UPDATE.getValue(), null, Locale.getDefault()));
		update.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				updateActionPerformed(evt);
			}
		});

		back.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
		back.setText(messageSource.getMessage(CMTMessageCode.BACK.getValue(), null, Locale.getDefault()));
		back.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				backActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup().addGap(100, 100, 100).addComponent(jLabel1,
								javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jPanel1Layout.createSequentialGroup().addGap(29, 29, 29)
										.addGroup(jPanel1Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
								.addGroup(
										jPanel1Layout.createSequentialGroup().addGap(52, 52, 52).addComponent(update)))
								.addGap(27, 27, 27)
								.addGroup(jPanel1Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(userid, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(username, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(name).addComponent(email)
										.addComponent(mobile, javax.swing.GroupLayout.DEFAULT_SIZE, 129,
												Short.MAX_VALUE)
										.addGroup(jPanel1Layout.createSequentialGroup().addGap(24, 24, 24)
												.addComponent(back)))))
						.addContainerGap(41, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(19, 19, 19)
						.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(26, 26, 26)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel2).addComponent(userid, javax.swing.GroupLayout.PREFERRED_SIZE, 17,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel3).addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(username, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGap(18, 18, 18)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel5).addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel6).addComponent(mobile, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(update).addComponent(back))
						.addContainerGap(23, Short.MAX_VALUE)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void backActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_backActionPerformed
		dispose();
		acServiceBackImpl.backButtonCode(this);
	}// GEN-LAST:event_backActionPerformed

	private void updateActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_updateActionPerformed

		try {
			UserDetailHistory detail = new UserDetailHistory(Integer.parseInt(userid.getText()), username.getText(),
					name.getText(), email.getText(), mobile.getText(), null, new Date().toString(), "update");
			String response = acServiceUserImpl.updateUserData(detail);
			JOptionPane.showMessageDialog(new JFrame(), response, response, JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}// GEN-LAST:event_updateActionPerformed

	/**
	 * @param args
	 *            the command line arguments
	 */

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton back;
	private javax.swing.JTextField email;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JTextField mobile;
	private javax.swing.JTextField name;
	private javax.swing.JButton update;
	private javax.swing.JLabel userid;
	private javax.swing.JLabel username;
	// End of variables declaration//GEN-END:variables

}
