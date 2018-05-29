/**
 * 
 */
package com.mfsi.hm.biztier.services;

import com.mfsi.hm.daotier.models.Configuration;

/**
 * @author shah
 *
 */
public interface CoreService {
	public Configuration findByConfiguration(String code);
}
