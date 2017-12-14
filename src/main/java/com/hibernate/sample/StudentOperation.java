package com.hibernate.sample;

import java.util.Iterator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class StudentOperation  {

	public static void main(String[] args) throws Myexception {
		Student s1 = new Student(12 , "ac", 106, "Pune");
		Student s2 = new Student(11 , "ab", 105, "PCMC");
		Student s3 = new Student(10 , "ad", 104, "Kolhapur");
		Student s4 = new Student(14 , "ae", 103, "Sangli");
		Student s5 = new Student(15 , "af", 102, "Mumabai");
		
		Student s6 = new Student(15, "af", 102, "Mumabai");
		OperateStud op = new OperateStud();
		/*op.Add(s1);
		op.Add(s2);
		op.Add(s3);
		op.Add(s4);
		op.Add(s5);
*/		 			
		
		//System.out.println(op.getStudent(-3));
		
		//System.out.println(op.getAllStudent());
		
		//System.out.println(op.delete(2));
		
		//op.UpdateStudent(s3);
		
		//System.out.println(op.getSame(4));
		
		//System.out.println(op.SearchData(s6, SampleCriteria.all));
		
		System.out.println(op.Search2Data(s6, SampleCriteria.Address,SampleCriteria.rollnumber));
	
		
	}

}
