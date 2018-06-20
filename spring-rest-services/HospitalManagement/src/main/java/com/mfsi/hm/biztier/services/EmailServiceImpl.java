/**
 * 
 */
package com.mfsi.hm.biztier.services;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.mfsi.hm.core.common.AESHelper;
import com.mfsi.hm.daotier.models.Email;
import com.mfsi.hm.daotier.services.EmailDataService;

/**
 * @author shah
 *
 */
public class EmailServiceImpl implements EmailService {

	private static final String SMTP_AUTH = "mail.smtp.auth";
	private static final String SMTP_TLS = "mail.smtp.starttls.enable";
	private static final String SMTP_READ_TIMEOUT = "mail.smtp.timeout";
	private static final String SMTP_CONNECTION_TIMEOUT = "mail.smtp.connectiontimeout";
	
	@Autowired
	private EmailDataService emailDataService;

	private static Logger logger = LogManager.getLogger(EmailServiceImpl.class.getName());

	@Override
	public Boolean sendEMail(String[] toEmails, String subject, String body) {
		boolean isMailSent = false;
		Email email = null;
		JavaMailSenderImpl mailSender = null;
		InternetAddress fromAddress=null;
		
		email = emailDataService.findActiveSMTP();
		if (email != null) {
			
			Properties props = new Properties();
			props.put(SMTP_AUTH, email.getAuth());
			props.put(SMTP_TLS, email.getStarttls());
			props.put(SMTP_READ_TIMEOUT, "60000");
			props.put(SMTP_CONNECTION_TIMEOUT, "60000");
			
			mailSender = new JavaMailSenderImpl();
			mailSender.setHost(email.getHost());
			mailSender.setPort(email.getPort());
			mailSender.setUsername(email.getUserName());
			mailSender.setPassword(AESHelper.decrypt(email.getPassword()));
			mailSender.setJavaMailProperties(props);
			
			MimeMessage mail = mailSender.createMimeMessage();
			MimeMessageHelper helper = null;
			
			try {
				mail.setContent(body, "text/html");
				helper = new MimeMessageHelper(mail, Boolean.FALSE, "utf-8");
				helper.setTo(toEmails);
				helper.setSubject(subject);
				fromAddress = new InternetAddress(email.getFromEmail(), email.getFromName());
				helper.setFrom(fromAddress);
				helper.setValidateAddresses(true);
				mailSender.send(mail);
				
				isMailSent = true;
			} catch (MailException | MessagingException e) {
				logger.error("Not able to send forgot password email.");
				logger.error(e.getMessage(), e);
			} catch (UnsupportedEncodingException e) {
				logger.error("Not able to send forgot password email.");
				logger.error(e.getMessage(), e);
			}
		}else{
			logger.error("No active email is present in the database. Kindly update database.");
		}
		return isMailSent;
	}

}
