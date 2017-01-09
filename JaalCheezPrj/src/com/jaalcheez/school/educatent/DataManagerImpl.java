package com.jaalcheez.school.educatent;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


import com.jaalsheez.school.base.IDataManager;

public class DataManagerImpl implements IDataManager {

	@Override
	public Connection getConnection() throws SQLException, NamingException{
		Context initContext = new
				 InitialContext();
				   Context envContext  = (Context)initContext.lookup("java:/comp/env");
				   DataSource datasource = (DataSource)envContext.lookup("jdbc/LocalEducatentDB");
				   Connection con = datasource.getConnection();
				   
		return con;
	}

}
