package com.email.service.service;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.email.service.constants.OrderConstants;
import com.email.service.payload.MailPayload;
import com.email.service.payload.OrderDetails;
import com.email.service.templates.OrderTemplate;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@Service
public class JavaSmtpGmailSenderService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    OrderTemplate orderTemplate;

    @Value("${spring.mail.username}")
    private String fromEmail;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(JavaSmtpGmailSenderService.class);

    public void sendMail(MailPayload inputEmailPayload) throws MessagingException{

        OrderDetails orderDetails = inputEmailPayload.getOrderDetails();
        LOGGER.info("Received Email Reqeust for Order No: {}",orderDetails.getOrderStatus());

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(fromEmail);
        helper.setTo(inputEmailPayload.getToEmail());
        helper.setSubject(inputEmailPayload.getSubject());

        LOGGER.info("Order Status for Order No: {} is: {}",orderDetails.getOrderNo(),orderDetails.getOrderStatus());

        String body = inputEmailPayload.getOrderDetails().getOrderStatus().equalsIgnoreCase(OrderConstants.CONFIRMED) ?
        orderTemplate.createSuccessBody(orderDetails.getCustName(), orderDetails.getOrderNo()) :
        orderTemplate.createFailureBody(orderDetails.getCustName(), orderDetails.getOrderNo());
    
        helper.setText(body, true);

        FileSystemResource image = new FileSystemResource(new File(OrderConstants.LOGO_PATH));

        helper.addInline("logo", image);

        LOGGER.debug("Sending Email for Order No: {}",orderDetails.getOrderNo());
        emailSender.send(message);
        LOGGER.info("Email Sent for Order No: {}",orderDetails.getOrderNo());

    }
    
}