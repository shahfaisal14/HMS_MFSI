/**
 * 
 */
package com.mfsi.hm.daotier.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfsi.hm.daotier.models.Configuration;
import com.mfsi.hm.daotier.repositories.ConfigurationRepository;

/**
 * @author shah
 *
 */
@Service
public class CoreDataServiceImpl implements CoreDataService {

	@Autowired
	private ConfigurationRepository configurationRepo;

	@Override
	public Configuration findByConfiguration(String code) {
		Configuration configuration = null;
		if(code != null && code.trim().length() > 0) {
			configuration = configurationRepo.findByCode(code);
		}
		return configuration;
	}

}
