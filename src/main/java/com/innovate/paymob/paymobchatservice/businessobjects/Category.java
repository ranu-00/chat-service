package com.innovate.paymob.paymobchatservice.businessobjects;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
public class Category {
	private int categoryCode;
	private String category;
	
	private List<Item> items = new ArrayList<>();
	
	public Category(){
		
	}
	public Category(int categoryCode, String category){
		this.categoryCode =  categoryCode;
		this.category = category;
	}

	public int getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public List<Item> getItems() {
		return items;
	}
	
	public void setItems(List<Item> items) {
		this.items = items;
	}
	
}
