/**
 * 
 */
package com.mfsi.hm.daotier.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mfsi.hm.daotier.models.LoginAttempts;

/**
 * @author shah
 *
 */
@Repository
public interface LoginAttemptsRepository extends CrudRepository<LoginAttempts, Long> {

	void deleteByUserId(String userId);

	LoginAttempts findByUserId(String userId);

}
