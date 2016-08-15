/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.customer.management.tool;

import javax.swing.UnsupportedLookAndFeelException;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.customer.management.tool.cache.CacheManager;
import com.customer.management.tool.ui.LoginForm;

/**
 *
 * @author Aviral
 */

@SpringBootApplication
public class CMTStarter {

	public static void main(String[] args) {
		
		for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Windows".equals(info.getName())) {
                try {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                break;
            }
        }
		ConfigurableApplicationContext context = new SpringApplicationBuilder(CMTStarter.class).headless(false).run(args);
		context.getBean(LoginForm.class).setVisible(true);
		context.getBean(LoginForm.class).setResizable(false);
		context.getBean(CacheManager.class).insertInCategoryMap();
		
	}
}
