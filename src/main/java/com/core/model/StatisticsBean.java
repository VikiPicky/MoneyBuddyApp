package com.core.model;

public class StatisticsBean {
	
	private String category;
	private double amount;
	private double taxAmount;
	


	public StatisticsBean(String category, double amount, double taxAmount) {
		super();
		this.category = category;
		this.amount = amount;
		this.taxAmount = taxAmount;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}
	
}
