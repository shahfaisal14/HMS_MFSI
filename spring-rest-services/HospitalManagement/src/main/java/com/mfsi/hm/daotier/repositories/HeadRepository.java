/**
 * 
 */
package com.mfsi.hm.daotier.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mfsi.hm.daotier.models.Head;

/**
 * @author shah
 *
 */
@Repository
public interface HeadRepository extends JpaRepository<Head, Long> {
	
}
