package com.jaalsheez.school.base;

import com.jaalcheez.school.educatent.DataManagerImpl;

public class ApplicationContext {

	private static IDataManager dataManager = null;
	
	private static void initDataManager()
	{
		dataManager = new DataManagerImpl();
	}
		
	public static IDataManager getDataManager()
	{
		return dataManager;
	}
	
	public static void init()
	{
		initDataManager();
	}
	
}
