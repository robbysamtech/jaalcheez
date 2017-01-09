package com.jaalsheez.school.base;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

public interface IDataManager {
	
	public Connection getConnection() throws SQLException, NamingException;

}
