package com.innovate.paymob.paymobchatservice.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.innovate.paymob.chatmodel.ChatCategoryModel;
import com.innovate.paymob.chatmodel.ChatMessageModel;
import com.innovate.paymob.chatmodel.CustomerMessage;
import com.innovate.paymob.paymobchatservice.businessobjects.ItemsResponse;
import com.innovate.paymob.paymobchatservice.businessobjects.OfferResponse;
import com.innovate.paymob.paymobchatservice.businessobjects.TransferResponse;
import com.innovate.paymob.paymobchatservice.dto.CategoryServiceDto;
import com.innovate.paymob.paymobchatservice.dto.ChatMessageDto;
import com.innovate.paymob.paymobchatservice.dto.CustomerMessageDto;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
@RefreshScope
public class CategoryServiceImpl implements CategoryService{
	public static final String ACCOUNT_SID = "AC816cefacab21575348571763119cb742";
	public static final String AUTH_TOKEN = "ea86fe2c282ddc3cf3967b325c3a0e05";
	
	@Value(value="${whatsapp.firstmessage}")
	private String firstMessage;
	@Autowired
	private CustomerMessageDto customerMessageDto;
	
	@Autowired
	private CategoryServiceDto categoryServicedto;
	
	@Autowired 
	private ChatMessageDto chatMessageDto;
	
	@Value(value="${url.moneyTransfer}")
	private String SEND_MONEY_MICRO_SERVICE_URL;;
	
	@Value(value="${url.offer}")
	private String OFFERS_MICRO_SERVICE_URL;
	@Value(value="${url.bucketList}")
	private String BUCKET_LIST_MICRO_SERVICE_URL;
	
	@Value(value="${whatsapp.mobilenumber}")
	private String whatsappMobileNumber;
	
	
	
	
	@Override
	public boolean save(ChatCategoryModel model) {	
		
		return categoryServicedto.save(model);
	}

	@Override
	public List<ChatCategoryModel> fetchAllData() {
		return categoryServicedto.fetchAllData();
	}

	@Override
	public String getResponse(Message messageModel,String mobileNumber) {
		 Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		 String message = messageModel.getBody();
		 mobileNumber =whatsappMobileNumber;
		 int chatMessageId = 0;
			CustomerMessage customerMobile  = customerMessageDto.findBymobileNumber(whatsappMobileNumber);
			 
			if(customerMobile ==null) {
				System.out.println(message);
				if("2".equals(message.replaceAll("[^0-9]", ""))){
					 // hit mayank service to respond customer 
					ItemsResponse items =callBucketListMicroService("items");
						Message
		                .creator(new PhoneNumber("whatsapp:+91"+whatsappMobileNumber), // to
		                        new PhoneNumber("whatsapp:+14155238886"), // from
		                        items.getCategory().toString())
		                .create();	
				 }else if("3".equals(message.replaceAll("[^0-9]", ""))) {
					 OfferResponse mess = callOffersMicroService(new Long("9898989898"));
						Message
		                .creator(new PhoneNumber("whatsapp:+91"+whatsappMobileNumber), // to
		                        new PhoneNumber("whatsapp:+14155238886"), // from
		                        mess.getOfferSummary())
		                .create();	
				 }else {
			CustomerMessage model = new CustomerMessage();
			model.setMessageCategoryId(1L);
			model.setChatMessageId(0L);
			model.setMobileNumber(mobileNumber);
			customerMessageDto.save(model);
			   Message
			   .creator(new PhoneNumber("whatsapp:+91"+whatsappMobileNumber), // to
                       new PhoneNumber("whatsapp:+14155238886"), 
                       firstMessage)
               .create();
				 }
			}else {
			
			
			if("1".equals(String.valueOf(customerMobile.getMessageCategoryId()))) {
				 //money transfer chat started
				 CustomerMessage modelx = customerMessageDto.findByCatIdAndMobileNumber(1L, whatsappMobileNumber);
				 long chatId = modelx.getChatMessageId();
				 if(chatId == 0) {
					 chatId++;
					 customerMessageDto.updateCustomerMessage(chatId, modelx.getMessageCategoryId(), modelx.getMobileNumber());
				}else {
									
					if(chatId == 1L) {
						//update name
						customerMessageDto.updateSenderNameNumber(chatId, message.replaceAll("[^0-9]", ""));
					}else if(chatId == 2L) {
						//update mobile number
						customerMessageDto.updateSenderMobileNumber(chatId, message.replaceAll("[^0-9]", ""));
					}else if(chatId == 3L) {
						// update amount to send
						customerMessageDto.updateSenderamount(chatId, message.replaceAll("[^0-9]", ""));
					}
					
					chatId++;
					customerMessageDto.updateCustomerMessage(chatId, modelx.getMessageCategoryId(), modelx.getMobileNumber());
				}
			
				
				if(message.contains("Yes") || chatId ==4L) {
					CustomerMessage model = customerMessageDto.findByCatIdAndMobileNumber(1L, whatsappMobileNumber);
					System.out.print(model.getAmount() + "  ==");
					System.out.print(model.getMobileNumber() +" ==");
					System.out.print(model.getSenderMobileNumber()+" ==");
					System.out.println(model.getSenderName()+" ==");
					customerMessageDto.updateCustomerMessage(0L, modelx.getMessageCategoryId(), modelx.getMobileNumber());
					// RestClient Hit to rahul Service
					TransferResponse mess = callSendMoneyMicroService(new Long(model.getMobileNumber()), new Long(model.getSenderMobileNumber()), Double.valueOf(3000));
					Message
	                .creator(new PhoneNumber("whatsapp:+91"+whatsappMobileNumber), // to
	                        new PhoneNumber("whatsapp:+14155238886"), // from
	                        mess.getMessage())
	                .create();
					
				}else {
					ChatMessageModel chat= chatMessageDto.findBymessageId(chatId);
					if(chat !=null)
					Message
			                .creator(new PhoneNumber("whatsapp:+91"+whatsappMobileNumber), // to
			                        new PhoneNumber("whatsapp:+14155238886"), // from
			                        chat.getMessage())
			                .create();					
						
				}
				// Hit money transfer service
			 }else if("2".equals(message)){
				 // hit mayank service to respond customer 
					Message
	                .creator(new PhoneNumber("whatsapp:+91"+whatsappMobileNumber), // to
	                        new PhoneNumber("whatsapp:+14155238886"), // from
	                        "{\"category\": [{\"categoryCode\":101,\"category\":\"Electronics\",\"items\": [{\"itemCode\": 3001,\"categoryCode\": 101,\"name\": \"AC\",\"brand\": Voltas\",\"price\": 35000.0},{\"itemCode\": 3002,\"categoryCode\": 101,\"name\": \"Laptop\",\"brand\": \"Dell\",\"price\": 49000.0}]}]}")
	                .create();	
			 }else if("3".equals(message)) {
				 
				 
					Message
	                .creator(new PhoneNumber("whatsapp:+91"+whatsappMobileNumber), // to
	                        new PhoneNumber("whatsapp:+14155238886"), // from
	                        "{ \"mobile\": 8010580426, \"offerSummary\": \"User(9898989898) has offer of loan amount upto 8010580426 INR with low cost emi from 1 year to 5 years, contact your Relationship Manager\" }")
	                .create();	
			 }else {
				 System.out.println("GG ==>> "+firstMessage);
				 return firstMessage;
			 }
			 
			}

			return null;
		
	}

	public TransferResponse callSendMoneyMicroService(Long debitMobileNumber,
				Long creditMobileNumber
				, double amount) {
	UriComponentsBuilder builder = UriComponentsBuilder
	.fromUriString(SEND_MONEY_MICRO_SERVICE_URL)
	// Add query parameter
	.queryParam("debitMobileNumber", debitMobileNumber)
	.queryParam("creditMobileNumber", creditMobileNumber)
	.queryParam("amount", amount);
	
	RestTemplate restTemplate = new RestTemplate();
	TransferResponse response = restTemplate.postForObject(
		  builder.toUriString(), null, TransferResponse.class);
	return response;
	}
	
	
	//Sample Case Method to call OFFERS MICRO_SERVICE
	public OfferResponse callOffersMicroService(Long mobile) {
		OfferResponse response =null;
		UriComponentsBuilder builder = UriComponentsBuilder
			    						.fromUriString(OFFERS_MICRO_SERVICE_URL)
									    // Add query parameter
									    .queryParam("mobile", mobile);

		RestTemplate restTemplate = new RestTemplate();
		response = restTemplate.postForObject(
								 builder.toUriString(), null, OfferResponse.class);
		return response;
	}
		
	//Sample Case Method to call BucketList MICRO_SERVICE
	public ItemsResponse callBucketListMicroService(String itemsInfo) {
		ItemsResponse response =null;
		UriComponentsBuilder builder = UriComponentsBuilder
			    						.fromUriString(BUCKET_LIST_MICRO_SERVICE_URL)
									    // Add query parameter
									    .queryParam("itemsInfo", itemsInfo);

		RestTemplate restTemplate = new RestTemplate();
		response = restTemplate.postForObject(
								 builder.toUriString(), null, ItemsResponse.class);
		return response;
	}
}
