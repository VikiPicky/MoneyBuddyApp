package com.core.model;

public class CategoryBean {
	private String categoryName;
	private int userId;
	
    public CategoryBean(int userId, String categoryName) {
        super();
         this.categoryName = categoryName;
         this.categoryName = categoryName;
       
    }
	
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}


}
