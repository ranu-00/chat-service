package com.innovate.paymob.paymobchatservice.dto;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovate.paymob.chatmodel.ChatCategoryModel;
import com.innovate.paymob.paymobchatservice.repository.CategoryRepo;
@Service
public class CategoryServiceDtoImpl implements CategoryServiceDto{

	@Autowired
	CategoryRepo categoryRepo;
	
	@Override
	public boolean save(ChatCategoryModel model) {
		
		ChatCategoryModel chatCategory = categoryRepo.save(model);		
		if (chatCategory != null)
			return true;
		
		return false;
	}

	@Override
	public List<ChatCategoryModel> fetchAllData() {
		return categoryRepo.findAll();
	}

	@Override
	public ChatCategoryModel findByCategoryName(String key) {		
		return categoryRepo.findByCategoryName(key);
	}
	
}
