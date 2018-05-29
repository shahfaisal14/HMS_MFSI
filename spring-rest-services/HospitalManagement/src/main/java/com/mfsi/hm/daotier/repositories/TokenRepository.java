/**
 * 
 */
package com.mfsi.hm.daotier.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mfsi.hm.daotier.models.Token;

/**
 * @author shah
 *
 */
@Repository
public interface TokenRepository extends CrudRepository<Token, Long>{

	public Token findByToken(String authToken);

}
