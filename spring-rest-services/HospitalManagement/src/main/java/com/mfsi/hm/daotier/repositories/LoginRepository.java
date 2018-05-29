/**
 * 
 */
package com.mfsi.hm.daotier.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mfsi.hm.daotier.models.Login;

/**
 * @author shah
 *
 */
@Repository
public interface LoginRepository extends CrudRepository<Login, Long> {

	@Query(value="SELECT lgn FROM Login lgn WHERE user.userId = :userId")
	public Login findLogin(@Param("userId")String userId);

}
