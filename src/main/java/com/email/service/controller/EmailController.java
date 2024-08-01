package com.email.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.email.service.payload.MailPayload;
import com.email.service.service.JavaSmtpGmailSenderService;

import lombok.SneakyThrows;

@RestController
@RequestMapping("/email-service")
public class EmailController {

    @Autowired
    JavaSmtpGmailSenderService mailSendService;
    
    @SneakyThrows
    @PostMapping("/send-mail")
    public String sendEmail(@RequestBody MailPayload inputEmailPayload){

        mailSendService.sendMail(inputEmailPayload);

        return "Email sent successfully";
 
        
    }

}
