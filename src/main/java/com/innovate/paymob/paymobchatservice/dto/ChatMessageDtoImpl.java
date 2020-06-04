package com.innovate.paymob.paymobchatservice.dto;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovate.paymob.chatmodel.ChatMessageModel;
import com.innovate.paymob.paymobchatservice.repository.ChatMessageRepo;

@Service
public class ChatMessageDtoImpl implements ChatMessageDto{

	@Autowired
	private ChatMessageRepo chatMessageRepo;
	@Override
	public ChatMessageModel findBymessageId(Long messageId) {
		return chatMessageRepo.findBymessageId(messageId);
	}

	
}
