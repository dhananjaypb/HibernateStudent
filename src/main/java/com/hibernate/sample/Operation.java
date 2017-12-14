package com.hibernate.sample;

import java.util.List;

import org.hibernate.Criteria;
/**
 * 
 * @author Dhananjay
 *
 */
public interface Operation  {
	
public boolean Add(Student t);

public Student getStudent(int i) throws Myexception;

public List getAllStudent();

public boolean delete(int i);

public boolean UpdateStudent (Student t);

public Object getSame(int i);

public List<Student> Search2Data(Student t, SampleCriteria... c);

public List<Student> SearchData(Student t, SampleCriteria c);
	

}
