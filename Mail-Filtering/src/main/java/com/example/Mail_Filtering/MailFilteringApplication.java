package com.example.Mail_Filtering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MailFilteringApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailFilteringApplication.class, args);
	}

	@Bean
	public RestTemplate restTempate(){
		return new RestTemplate();
	}

}
