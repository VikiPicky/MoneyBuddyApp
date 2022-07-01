package com.core.model;

import java.util.Date;

public class RecordBean {	
	
	private int recordId;
	private String category;
	private String record;
	private double amount;
	private Date date;
	private double taxAmount;
	private String comment;
	private int userId;
		
		public RecordBean(int recordId, String category, String record, double amount, Date date, double taxAmount,
			String comment, int userId) {
		super();
		this.recordId = recordId;
		this.category = category;
		this.record = record;
		this.amount = amount;
		this.date = date;
		this.taxAmount = taxAmount;
		this.comment = comment;
		this.userId = userId;
	}
		
		public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public int getRecordId() {
		return recordId;
	}
	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public double getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getRecord() {
		return record;
	}
	public void setRecord(String record) {
		this.record = record;
	}
	

}
