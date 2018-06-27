/**
 * 
 */
package com.mfsi.hm.daotier.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mfsi.hm.daotier.models.Doctor;

/**
 * @author shah
 *
 */
public interface DoctorRepository extends CrudRepository<Doctor, Long> {

	public List<Doctor> findAll();
}
