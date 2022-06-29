package com.core.model;

public class CategoryBean {
	
	private int categoryId;
	private String categoryName;
	private int userId;
	
    public CategoryBean(int categoryId, String categoryName, int userId) {
        super();
        this.setCategoryId(categoryId);
         this.categoryName = categoryName;
         this.userId = userId;
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

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}


}
