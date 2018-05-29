/**
 * 
 */
package com.mfsi.hm.daotier.services;

import com.mfsi.hm.daotier.models.Configuration;

/**
 * @author shah
 *
 */
public interface CoreDataService {
	public Configuration findByConfiguration(String code);
}
