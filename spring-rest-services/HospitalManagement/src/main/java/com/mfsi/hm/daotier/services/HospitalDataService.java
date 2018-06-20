/**
 * 
 */
package com.mfsi.hm.daotier.services;

import com.mfsi.hm.daotier.models.Hospital;

/**
 * @author shah
 *
 */
public interface HospitalDataService {

	public Hospital addHospital(Hospital hospital);
	
	public Hospital findByName(String name);

}
