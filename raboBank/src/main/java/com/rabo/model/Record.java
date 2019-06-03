package com.rabo.model;

import javax.xml.bind.annotation.XmlAttribute;

public class Record {

	private Integer reference;
	private String accountNumber;
	private double startBalance;
	private double mutation;
	private String description;
	private double endBalance;
	@XmlAttribute(name="reference")
	public int getReference() {
		return reference;
	}
	public void setReference(int reference) {
		this.reference = reference;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getStartBalance() {
		return startBalance;
	}
	public void setStartBalance(double startBalance) {
		this.startBalance = startBalance;
	}
	public double getMutation() {
		return mutation;
	}
	public void setMutation(double mutation) {
		this.mutation = mutation;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getEndBalance() {
		return endBalance;
	}
	public void setEndBalance(double endBalance) {
		this.endBalance = endBalance;
	}
}
