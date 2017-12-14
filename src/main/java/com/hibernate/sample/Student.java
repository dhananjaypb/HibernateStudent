package com.hibernate.sample;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Student implements Serializable {

@Id
@Column
//@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int StdID;
@Column
	private String StdName;
@Column
	private int StdRollno;
@Column
	private String StdAddress;



	public Student() {
	super();
}
	public Student(int stdID, String stdName, int stdRollno, String stdAddress) {
		super();
		StdID = stdID;
		StdName = stdName;
		StdRollno = stdRollno;
		StdAddress = stdAddress;
	}
	public int getStdID() {
		return StdID;
	}
	public void setStdID(int stdID) {
		StdID = stdID;
	}
	public String getStdName() {
		return StdName;
	}
	public void setStdName(String stdName) {
		StdName = stdName;
	}
	public int getStdRollno() {
		return StdRollno;
	}
	public void setStdRollno(int stdRollno) {
		StdRollno = stdRollno;
	}
	public String getStdAddress() {
		return StdAddress;
	}
	public void setStdAddress(String stdAddress) {
		StdAddress = stdAddress;
	}
	@Override
	public String toString() {
		return "Student [StdID=" + StdID + ", StdName=" + StdName + ", StdRollno=" + StdRollno + ", StdAddress="
				+ StdAddress + "]";
	}

}
