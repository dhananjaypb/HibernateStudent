package com.hibernate.sample;

import javax.transaction.Transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static SessionFactory sf = null;
	
	private  HibernateUtil()
	{
		
	}
	
	public static  SessionFactory getSessionFactory()
	{
		if (sf==null)
		{
			Configuration cf= new Configuration();
			 sf = cf.configure().buildSessionFactory();
		}
		return sf;
	}
		
	public static void CloseResource(Session s,org.hibernate.Transaction tran)
	{
		tran.commit();
		s.close();
	}
	
	public static void DataValidate(Student t)
	{
		if(t==null)
			throw new Myexception("Object shoul not be null");
			
	}

}
