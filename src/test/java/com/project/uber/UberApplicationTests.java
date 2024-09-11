package com.project.uber;

import com.project.uber.services.EmailSenderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UberApplicationTests {

	@Autowired
	private EmailSenderService emailSenderService;
	@Test
	void contextLoads() {

		emailSenderService.sendEmail("enigmasr964@gmail.com","Test mail", "Turn your pain into power");
	}

	@Test
	void sendEmailToMultiple() {

		String[] emails = {"enigmasr964@gmail.com","2016kuec2051@iiitkota.ac.in"};
		emailSenderService.sendEmail(emails,"Test mail", "Turn your pain into power");
	}

}
