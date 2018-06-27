/**
 * 
 */
package com.mfsi.hm.daotier.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfsi.hm.daotier.models.Doctor;
import com.mfsi.hm.daotier.repositories.DoctorRepository;

/**
 * @author shah
 *
 */
@Service("doctorDataService")
public class DoctorDataServiceImpl implements DoctorDataService {
	
	@Autowired
	DoctorRepository doctorRepository;
	
	@Override
	public Doctor createDoctor(Doctor doctor) {
		Doctor newDoctor = null;
		if(doctor != null){
			newDoctor = doctorRepository.save(doctor);
		}
		return newDoctor;
	}
	
	@Override
	public List<Doctor> getDoctorsList(){
		return doctorRepository.findAll();
	}

}
