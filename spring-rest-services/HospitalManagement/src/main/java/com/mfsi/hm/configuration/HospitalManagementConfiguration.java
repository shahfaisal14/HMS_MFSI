/**
 * 
 */
package com.mfsi.hm.configuration;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.mfsi.hm.core.filters.AuthInterceptor;

/**
 * @author shah
 *
 */
@Configuration
public class HospitalManagementConfiguration {
	
	@Autowired
	AuthInterceptor authInterceptor;
	
	private static final String MAIL_HOST = "spring.mail.host";
	private static final Integer MAIL_PORT = 587;
	private static final String MAIL_USERNAME = "spring.mail.username";
	private static final String MAIL_PASSWORD = "spring.mail.password";
	private static final String MAIL_SMTP_AUTH = "pring.mail.properties.mail.smtp.auth";
	private static final String MAIL_SMTP_STARTTLS_ENABLE = "spring.mail.properties.mail.smtp.starttls.enable";
	private static final String MAIL_DEBUG = "spring.mail.debug";
	private static final String MAIL_TRANSPORT_PROTOCOL = "spring.mail.protocol";
	@Bean
    public WebMvcConfigurer corsConfigurer() 
    {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
            
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
        		 registry.addInterceptor(authInterceptor);
            }
        };
    }
	
	@Bean
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost(MAIL_HOST);
	    mailSender.setPort(MAIL_PORT);
	     
	    mailSender.setUsername(MAIL_USERNAME);
	    mailSender.setPassword(MAIL_PASSWORD);
	     
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", MAIL_TRANSPORT_PROTOCOL);
	    props.put("mail.smtp.auth", MAIL_SMTP_AUTH);
	    props.put("mail.smtp.starttls.enable", MAIL_SMTP_STARTTLS_ENABLE);
	    props.put("mail.debug", MAIL_DEBUG);
	     
	    return mailSender;
	}
	
}
