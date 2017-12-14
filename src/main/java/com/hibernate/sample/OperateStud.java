package com.hibernate.sample;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import javax.swing.plaf.basic.BasicTreeUI.TreeCancelEditingAction;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
/**
 * 
 * @author Dhananjay
 *
 */


public class OperateStud implements Operation
	{

	
	public boolean  Add(Student t) {
		dataValidator(t);
		Session s=HibernateUtil.getSessionFactory().openSession();
		Transaction tran = s.beginTransaction();
		s.save(t);
		HibernateUtil.CloseResource(s, tran);
		return true;

	}
	public static void dataValidator(Student s) {
		if (s == null || s.getStdAddress().trim().isEmpty() || s.getStdName().trim().isEmpty() || s.getStdID() <= 0
				|| s.getStdRollno() <= 0)
			throw new Myexception("Invalid data passed as an entry for Database.");
	}
	
	public Student getStudent(int i) throws Myexception {
		if (i<=0) {
			throw new Myexception("Negative value not accepted");
		}
		Session s=HibernateUtil.getSessionFactory().openSession();
		Transaction tran = s.beginTransaction();
			Student Obj;
			Obj = s.get(Student.class, i );
			
			HibernateUtil.CloseResource(s, tran);
			return Obj;
		}

	public List <Student> getAllStudent() {
		List l = new ArrayList ();	
		
		Student Obj;
		Session s=HibernateUtil.getSessionFactory().openSession();
		Transaction tran = s.beginTransaction();
		l=s.createQuery("from Student").getResultList();
		HibernateUtil.CloseResource(s, tran);
		return l;
		}

	public boolean delete(int i) {
		if (i<=0) {
			throw new Myexception("Negative value not accepted");
		}
		Session	s =HibernateUtil.getSessionFactory().openSession();
		Transaction tra = s.beginTransaction();
		Student Obj = s.get(Student.class, i);
		s.remove(Obj);
		HibernateUtil.CloseResource(s, tra);
		return true;
		}

	public boolean UpdateStudent(Student t) {
		dataValidator(t);
		Scanner sc =new Scanner(System.in);
		Session s=HibernateUtil.getSessionFactory().openSession();
		Transaction tran = s.beginTransaction();
		System.out.println("Please enter choice for update\n1.Name 2.Roll number 3.Address");
		int choice =sc.nextInt();
		switch (choice) {
		case 1:
				System.out.println("Enter new name");
				String a = sc.next();
				t.setStdName(a);
				s.update(t);;
				s.save(t);
				HibernateUtil.CloseResource(s, tran);
				break;
		case 2:
			System.out.println("Enter new roll number");
			int b = sc.nextInt();
			t.setStdRollno(b);
			s.update(t);;
			s.save(t);
			HibernateUtil.CloseResource(s, tran);
			break;
		case 3:
			System.out.println("Enter new Address");
			String c = sc.next();
			t.setStdAddress(c);
			s.update(t);
			s.save(t);
			HibernateUtil.CloseResource(s, tran);
			break;
	}	
		return true;
	
	}

	public List<Student> getSame(int i) {
		if (i<=0) {
			throw new Myexception("Negative value not accepted");
		}
		Student Std;
		Session s=HibernateUtil.getSessionFactory().openSession();
		Transaction tran = s.beginTransaction();
		Student Obj = s.get(Student.class,i);
		List<Student> l= getAllStudent();
		List<Student> searchResult = new ArrayList<Student>();
		Iterator<Student> itr = l.iterator();
		while(itr.hasNext())
		{
			Std = (Student)itr.next();
			if(Obj.getStdAddress().equals(Std.getStdAddress())) {
				searchResult.add(Std);
				if(i==Std.getStdID())
					searchResult.remove(Std);
			}
		}
			
		HibernateUtil.CloseResource(s, tran);
		return searchResult;
				
		
	}

	public List<Student> SearchData(Student t, SampleCriteria c) {
		dataValidator(t);	
	List<Student> l =new ArrayList<Student>();
	HibernateUtil.DataValidate(t);
	Session s= HibernateUtil.getSessionFactory().openSession();
	Transaction tra = s.beginTransaction();
	Criteria cr = s.createCriteria(Student.class);
	switch (c) {
	case NAME:
	l=	cr.add(Restrictions.eq("StdName", t.getStdName())).list();
	HibernateUtil.CloseResource(s, tra);
		break;
	case rollnumber:
		l=	cr.add(Restrictions.eq("StdRollno", t.getStdRollno())).list();
		HibernateUtil.CloseResource(s, tra);
			break;
	case Address:
		l= cr.add(Restrictions.eq("StdAddress", t.getStdAddress())).list();
		HibernateUtil.CloseResource(s, tra);
		break;
	case all:
		l= getAllStudent();
		HibernateUtil.CloseResource(s, tra);
		break;
	default:		
		throw new Myexception("Invalid object");
		
	}
		return l;
	}		
	
	
	public List<Student> Search2Data(Student t, SampleCriteria... criteria) {
		dataValidator(t);
		if (criteria.length <= 0 || criteria.length > 3)
			throw new Myexception("Invalid number of search parameters passed.");

		for (int i = 0; i < criteria.length - 1; i++) {
			if (criteria[i].equals(criteria[i + 1]))
				throw new Myexception("Same search parameters used.");
		}

		List<Student> l = new ArrayList<Student>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = s.beginTransaction();
		Criteria cr = s.createCriteria(Student.class);

		if (criteria.length == 3) {
			if (criteria[0].equals(criteria[2]))
				throw new Myexception("Same search parameters used.");
			else {
				cr.add(Restrictions.eq("StdName", t.getStdName()));
				cr.add(Restrictions.eq("StdAddress", t.getStdAddress()));
				cr.add(Restrictions.eq("StdRollno", t.getStdRollno()));
				return cr.list();
			}
		}

		else if (criteria.length == 2) {
			switch (criteria[0]) {
			case NAME:
				switch (criteria[1]) {
				case Address:
					cr.add(Restrictions.eq("StdName", t.getStdName()));
					cr.add(Restrictions.eq("StdAddress", t.getStdAddress()));
					l = cr.list();
					break;

				case rollnumber:
					cr.add(Restrictions.eq("StdName", t.getStdName()));
					cr.add(Restrictions.eq("StdRollno", t.getStdRollno()));
					l = cr.list();
					break;

				case all:
					throw new Myexception("Second Search Parameter Invalid. Please enter a different Second criteria");

				default:
					throw new Myexception("Invalid second search criteria mentioned.");
				}
				HibernateUtil.CloseResource(s, transaction);
				break;

			case Address:
				switch (criteria[1]) {
				case NAME:
					cr.add(Restrictions.eq("StdAddress", t.getStdAddress()));
					cr.add(Restrictions.eq("StdName", t.getStdName()));
					l = cr.list();
					break;

				case rollnumber:
					cr.add(Restrictions.eq("StdAddress", t.getStdAddress()));
					cr.add(Restrictions.eq("StdRollno", t.getStdRollno()));
					l = cr.list();
					break;

				case all:
					throw new Myexception("Second Search Parameter Invalid. Please enter a different Second criteria");

				default:
					throw new Myexception("Invalid second search criteria mentioned.");
				}

				HibernateUtil.CloseResource(s, transaction);
				break;

			case rollnumber:
				switch (criteria[1]) {
				case NAME:
					cr.add(Restrictions.eq("StdName", t.getStdName()));
					cr.add(Restrictions.eq("StdRollno", t.getStdRollno()));
					l = cr.list();
					break;

				case Address:
					cr.add(Restrictions.eq("StdRollno", t.getStdRollno()));
					cr.add(Restrictions.eq("StdAddress", t.getStdAddress()));
					l = cr.list();
					break;

				case all:
					throw new Myexception("Second Search Parameter Invalid. Please enter a different Second criteria");

				default:
					throw new Myexception("Invalid second search criteria mentioned.");
				}
				HibernateUtil.CloseResource(s, transaction);
				break;

			case all:
				switch (criteria[1]) {
				case NAME:
				case Address:
				case rollnumber:
					throw new Myexception("'ALL' search criteria cannot have a second parameter");

				default:
					throw new Myexception("Invalid first search criteria mentioned.");
				}
			}
		}

		else {
			switch (criteria[0]) {
			case NAME:
				l = cr.add(Restrictions.eq("StdName", t.getStdName())).list();
				HibernateUtil.CloseResource(s, transaction);
				break;

			case Address:
				l = cr.add(Restrictions.eq("StdAddress", t.getStdAddress())).list();
				HibernateUtil.CloseResource(s, transaction);
				break;

			case rollnumber:
				l = cr.add(Restrictions.eq("StdRollno", t.getStdRollno())).list();
				HibernateUtil.CloseResource(s, transaction);
				break;

			case all:
				l = getAllStudent();
				break;

			default:
				throw new Myexception("Invalid first search criteria mentioned.");

			}

		}

		return l;
	}	}
	

	
	

		
	


