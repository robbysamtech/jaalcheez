package com.jaalcheez.school.servlet;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.jaalcheez.school.base.ApplicationContext;

public class ConfiguratorServlet extends GenericServlet {
	
	public void init(ServletConfig sc) throws ServletException
	{
		super.init(sc);
		System.out.println("Load on startup");
		ApplicationContext.init();
		// load the configuration file.
		// Initialize the connection
		// Initialize the implementation classes and set it to a holder class. (IConfigurationManager)
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
}
