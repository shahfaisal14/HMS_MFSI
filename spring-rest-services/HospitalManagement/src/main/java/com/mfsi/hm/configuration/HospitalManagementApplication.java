package com.mfsi.hm.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.mfsi.hm"})
@EnableJpaRepositories(basePackages="com.mfsi.hm.daotier.repositories")
@EntityScan(basePackages="com.mfsi.hm.daotier.models")
public class HospitalManagementApplication {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = SpringApplication.run(HospitalManagementApplication.class, args); 

		for (@SuppressWarnings("unused") String name : applicationContext.getBeanDefinitionNames()) {
			//System.out.println(name);
		}
	}
}
