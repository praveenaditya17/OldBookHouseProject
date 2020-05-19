package com.oldBookSell.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * This is MailSend that is use to send the mail to registerd user
 * @author  Kundan,Praveen
 * @version 1.0
 * @since 2020-05-18
*/
public class MailSend {
	
	@Autowired
    private JavaMailSender sender;
	
	public MailSend() {
	}
	
	public  String sendMail() {
		
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo("kumarkundan380@gmail.com");
            helper.setText("Greetings :) this is the information for text");
            helper.setText("by Kundan Kumar");
            helper.setSubject("OldBookStore");
        } catch (MessagingException exception) {
            exception .printStackTrace();
            return "Error while sending mail ..";
        }
        sender.send(message);
        return "Mail Sent Success!";
    }
}
