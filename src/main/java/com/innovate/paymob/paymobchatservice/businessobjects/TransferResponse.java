package com.innovate.paymob.paymobchatservice.businessobjects;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value="prototype")
public class TransferResponse {
private String status;
	
	private String message;

	
	
	public TransferResponse(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public TransferResponse() {
		// TODO Auto-generated constructor stub
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
