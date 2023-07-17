package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.EmailService;



@Controller
public class EmailController {

	private final String FROM = "09063597254abc@gmail.com";
	private final String TO = "nttrungbi1996@gmail.com";

	@Autowired
	private EmailService emailService;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("from",FROM);
		model.addAttribute("to",TO);
		return "index";
	}

	@PostMapping("/sendEmail")
	public String sendMessage(
	//			@RequestParam String fromEmail,
	//			@RequestParam String toEmail,
				@RequestParam String subject,
				@RequestParam String body 
				) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		
		simpleMailMessage.setFrom(FROM);
		simpleMailMessage.setTo(TO);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(body);
		
		emailService.sendMessage(simpleMailMessage);

		return "successfully";
	}

}