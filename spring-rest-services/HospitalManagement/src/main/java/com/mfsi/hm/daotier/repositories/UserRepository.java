/**
 * 
 */
package com.mfsi.hm.daotier.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mfsi.hm.daotier.models.User;

/**
 * @author shah
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	public User findByUserId(String userId);
	
}
