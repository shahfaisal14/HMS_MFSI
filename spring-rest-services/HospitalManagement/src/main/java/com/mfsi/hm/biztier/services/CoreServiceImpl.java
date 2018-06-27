/**
 * 
 */
package com.mfsi.hm.biztier.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfsi.hm.daotier.models.Configuration;
import com.mfsi.hm.daotier.services.CoreDataService;

/**
 * @author shah
 *
 */
@Service("coreService")
public class CoreServiceImpl implements CoreService {

	@Autowired
	private CoreDataService coreDataService;

	public void setCoreDataService(CoreDataService coreDataService) {
		this.coreDataService = coreDataService;
	}

	@Override
	public Configuration findByConfiguration(String code) {
		Configuration configuration = null;
		if (code != null) {
			configuration = coreDataService.findByConfiguration(code);
		}
		return configuration;
	}

}
