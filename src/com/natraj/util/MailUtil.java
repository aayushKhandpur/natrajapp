package com.natraj.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.natraj.entity.Customer;

public class MailUtil {
	
	
	public static void sendMailForRegistration(Customer customer){
		String resetLink = "http://localhost:8080/activateAccount"+"?email="+customer.getEmail()+"&token="+customer.getEmailValidationCode();
		String subject = "[Natraj System] Please activate your account";
		String messageText = "Hey, Welcome to Natraj System.\n\n" +
				"Use the following link to activate your account:\n\n"+resetLink+"\n\n"+
				"Thanks,\n"+"Natraj Team";
		sendMail(subject, messageText, customer.getEmail());
	}
	
	public static void sendMail(String subject, String messageText, String userMail){
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("","");
					}
				});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(""));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(userMail));
			message.setSubject(subject);
			message.setText(messageText);
			Transport.send(message);
			System.out.println("Email successfully sent.");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}