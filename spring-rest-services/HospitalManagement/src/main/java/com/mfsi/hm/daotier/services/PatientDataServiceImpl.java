/**
 * 
 */
package com.mfsi.hm.daotier.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfsi.hm.daotier.models.Patient;
import com.mfsi.hm.daotier.repositories.PatientRepository;

/**
 * @author shah
 *
 */
@Service("patientDataService")
public class PatientDataServiceImpl implements PatientDataService {

	
	@Autowired
	PatientRepository patientRepository;
	
	
	@Override
	public Patient registerPatient(Patient patient) {
		Patient newPatient = null;
		if(patient != null){
			newPatient = patientRepository.save(patient);
		}
		return newPatient;
	}

}
