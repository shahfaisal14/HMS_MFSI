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
	
	private static final String MAIL_USERNAME = "spring.mail.username";
	private static final String MAIL_PASSWORD = "spring.mail.password";

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
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	     
	    mailSender.setUsername(MAIL_USERNAME);
	    mailSender.setPassword(MAIL_PASSWORD);
	     
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	     
	    return mailSender;
	}
	
}
