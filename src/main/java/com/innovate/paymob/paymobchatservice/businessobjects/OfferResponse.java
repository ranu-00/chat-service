package com.innovate.paymob.paymobchatservice.businessobjects;

public class OfferResponse {
	private Long mobile;
	private String offerSummary;
	public Long getMobile() {
		return mobile;
	}
	public OfferResponse(){
		
	}
	public OfferResponse(Long mobile, String offerSummary) {
		this.mobile = mobile;
		this.offerSummary = offerSummary;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}
	public String getOfferSummary() {
		return offerSummary;
	}
	public void setOfferSummary(String offerSummary) {
		this.offerSummary = offerSummary;
	}
	
}
