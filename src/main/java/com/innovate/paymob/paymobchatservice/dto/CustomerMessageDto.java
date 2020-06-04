package com.innovate.paymob.paymobchatservice.dto;

import org.springframework.data.repository.query.Param;

import com.innovate.paymob.chatmodel.CustomerMessage;

public interface CustomerMessageDto {
	public CustomerMessage findByCatIdAndMobileNumber(Long messageCategoryId,String mobileNumber);

	public void updateCustomerMessage(Long chatMessageId, Long messageCategoryId, String mobileNumber);
	 public CustomerMessage save(CustomerMessage model);
		public CustomerMessage findBymobileNumber(String mobileNumber);
		public void updateSenderMobileNumber(Long chatMessageId,String senderMobileNumber);
		public void updateSenderNameNumber(Long chatMessageId,String senderName);
		public void updateSenderamount(Long chatMessageId,String amount);

}
