package com.innovate.paymob.paymobchatservice.dto;
import com.innovate.paymob.chatmodel.ChatMessageModel;

public interface ChatMessageDto {
	
	 public ChatMessageModel findBymessageId(Long chatMessageId);

}
