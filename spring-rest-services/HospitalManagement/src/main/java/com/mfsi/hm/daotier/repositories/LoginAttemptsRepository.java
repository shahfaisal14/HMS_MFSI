/**
 * 
 */
package com.mfsi.hm.daotier.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mfsi.hm.daotier.models.LoginAttempts;

/**
 * @author shah
 *
 */
@Repository
public interface LoginAttemptsRepository extends CrudRepository<LoginAttempts, Long> {

	@Transactional
	void deleteByUserId(String userId);

	LoginAttempts findByUserId(String userId);

}
