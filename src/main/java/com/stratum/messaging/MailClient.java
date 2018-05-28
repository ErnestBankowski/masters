package com.stratum.messaging;

import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class MailClient {
 
    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    MailContentBuilder contentBuilder;
 
    public void prepareAndSend(String recipient, String subject, String message) {
    	System.out.println("About to send email to: "+recipient);
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(new InternetAddress("ryansimmons7@gmail.com", "Stratum Application"));
            messageHelper.setTo(recipient);
            messageHelper.setSubject(subject);
            String content = contentBuilder.build(subject, message);
            messageHelper.setText(content, true);
        };
        try {
            mailSender.send(messagePreparator);
            System.out.println("Message sent.");
        } catch (MailException e) {
        }
    }
 
}