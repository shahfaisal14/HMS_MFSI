/**
 * 
 */
package com.mfsi.hm.biztier.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author shah
 *
 */
@Service("emailService")
public class EmailServiceImpl implements EmailService {
	
	private static Logger logger = LogManager.getLogger(EmailServiceImpl.class.getName());
	
	@Autowired
	JavaMailSender emailSender;

	@Override
	public Boolean sendEMail(String[] toEmails, String subject, String body) {
		boolean isMailSent = false;
		
		SimpleMailMessage message = new SimpleMailMessage(); 
	        
		try {
			message.setTo(toEmails); 
	        message.setSubject(subject); 
	        message.setText(body);
	        emailSender.send(message);
			isMailSent = true;
		} catch (MailException e) {
			logger.error("Not able to send email.");
			logger.error(e.getMessage(), e);
		}
		return isMailSent;
	}

}
