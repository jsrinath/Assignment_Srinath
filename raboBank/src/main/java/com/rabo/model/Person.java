package com.rabo.model;

import java.util.Date;

public class Person {
	private String firstName;
	private String surName;
	private int issueCount;
	private String dateofBirth;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSurName() {
		return surName;
	}
	public void setSurName(String surName) {
		this.surName = surName;
	}
	public int getIssueCount() {
		return issueCount;
	}
	public void setIssueCount(int issueCount) {
		this.issueCount = issueCount;
	}
	public String getDateofBirth() {
		return dateofBirth;
	}
	public void setDateofBirth(String dateofBirth) {
		this.dateofBirth = dateofBirth;
	}
}
