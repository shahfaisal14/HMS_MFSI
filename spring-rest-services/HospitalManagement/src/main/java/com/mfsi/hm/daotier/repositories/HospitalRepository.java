/**
 * 
 */
package com.mfsi.hm.daotier.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.mfsi.hm.daotier.models.Hospital;

/**
 * @author shah
 *
 */
@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
	
	public Hospital findByName(String name);
	
}
