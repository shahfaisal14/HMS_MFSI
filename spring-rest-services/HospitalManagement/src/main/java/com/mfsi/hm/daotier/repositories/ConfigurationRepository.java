/**
 * 
 */
package com.mfsi.hm.daotier.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mfsi.hm.daotier.models.Configuration;

/**
 * @author shah
 *
 */
@Repository
public interface ConfigurationRepository extends CrudRepository<Configuration, Long> {

	Configuration findByCode(String code);

}
