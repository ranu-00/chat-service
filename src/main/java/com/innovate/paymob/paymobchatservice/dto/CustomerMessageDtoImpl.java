package com.innovate.paymob.paymobchatservice.dto;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovate.paymob.chatmodel.ChatMessageModel;
import com.innovate.paymob.chatmodel.CustomerMessage;
import com.innovate.paymob.paymobchatservice.repository.CustomerMessageRepo;
@Service
public class CustomerMessageDtoImpl implements CustomerMessageDto{
    @Autowired
	private CustomerMessageRepo customerMessageRepo;
	
	@Override
	public CustomerMessage findByCatIdAndMobileNumber(Long messageCategoryId, String mobileNumber) {
		// TODO Auto-generated method stub
		return customerMessageRepo.findByCatIdAndMobileNumber(messageCategoryId, mobileNumber);
	}

	@Override
	@Transactional
	public void updateCustomerMessage(Long chatMessageId, Long messageCategoryId, String mobileNumber) {
		customerMessageRepo.updateCustomerMessage(chatMessageId, messageCategoryId, mobileNumber);
	}

	@Override
	public CustomerMessage save(CustomerMessage model) {
		// TODO Auto-generated method stub
		return customerMessageRepo.save(model);
	}

	@Override
	public CustomerMessage findBymobileNumber(String mobileNumber) {
		// TODO Auto-generated method stub
		return customerMessageRepo.findBymobileNumber(mobileNumber);
	}

	@Override
	@Transactional
	public void updateSenderMobileNumber(Long chatMessageId, String senderMobileNumber) {
		customerMessageRepo.updateSenderMobileNumber(chatMessageId, senderMobileNumber);
		
	}

	@Override
	@Transactional
	public void updateSenderNameNumber(Long chatMessageId, String senderName) {
		// TODO Auto-generated method stub
		customerMessageRepo.updateSenderNameNumber(chatMessageId, senderName);
	}

	@Override
	@Transactional
	public void updateSenderamount(Long chatMessageId, String amount) {
		// TODO Auto-generated method stub
		customerMessageRepo.updateSenderamount(chatMessageId, amount);
		
	}

	
}
