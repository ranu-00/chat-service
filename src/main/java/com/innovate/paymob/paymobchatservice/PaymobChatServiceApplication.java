package com.innovate.paymob.paymobchatservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages="com.innovate.paymob.chatmodel")
public class PaymobChatServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymobChatServiceApplication.class, args);
	}

}
