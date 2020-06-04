package com.innovate.paymob.paymobchatservice.businessobjects;
import java.util.List;
public class ItemsResponse {
private List<Category> category;
	
	public ItemsResponse() {
		
	}
	public ItemsResponse(List<Category> category) {
		this.category = category;
	}

	public List<Category> getCategory() {
		return category;
	}

	public void setCategory(List<Category> category) {
		this.category = category;
	}
	
}
