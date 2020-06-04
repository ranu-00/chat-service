package com.innovate.paymob.paymobchatservice.services;
import java.util.List;

import com.innovate.paymob.chatmodel.ChatCategoryModel;
import com.twilio.rest.api.v2010.account.Message;

public interface CategoryService {
	
	public boolean save(ChatCategoryModel model);
	
	public List<ChatCategoryModel> fetchAllData();
	
	public String getResponse(Message message,String mobileNumber);

}
