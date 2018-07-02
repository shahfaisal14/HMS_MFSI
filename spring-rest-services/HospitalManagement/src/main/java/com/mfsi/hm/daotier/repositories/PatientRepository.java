/**
 * 
 */
package com.mfsi.hm.daotier.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mfsi.hm.daotier.models.Patient;

/**
 * @author shah
 *
 */
public interface PatientRepository extends JpaRepository<Patient, Long> {

}
