/**
 * 
 */
package com.mfsi.hm.daotier.services;

import java.util.List;

import com.mfsi.hm.daotier.models.Hospital;

/**
 * @author shah
 *
 */
public interface HospitalDataService {

	public Hospital addHospital(Hospital hospital);
	
	public Hospital findByName(String name);

	public List<Hospital> getHospitalsList();

	public Hospital getHospitalById(Long dataStoreId);

}
