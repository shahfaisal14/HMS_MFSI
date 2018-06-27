/**
 * 
 */
package com.mfsi.hm.daotier.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mfsi.hm.daotier.models.Email;

/**
 * @author shah
 *
 */
@Repository
public interface EmailRepository extends CrudRepository<Email, Long> {
	public Email findByIsActive(Boolean isActive);
}
