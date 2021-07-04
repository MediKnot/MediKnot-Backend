package com.hack.azure.mediknot;

import com.hack.azure.mediknot.service.EmailService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MediKnotApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediKnotApplication.class, args);
	}

	@Bean
	public EmailService createEmailBean(){
		return new EmailService("mediknot@gmail.com", "mediknot@ms");
	}
}
