/**
 * 
 */
package com.mfsi.hm.daotier.services;

import java.util.List;

import com.mfsi.hm.daotier.models.Doctor;

/**
 * @author shah
 *
 */
public interface DoctorDataService {

	public Doctor createDoctor(Doctor doctor);

	public List<Doctor> getDoctorsList();

}
