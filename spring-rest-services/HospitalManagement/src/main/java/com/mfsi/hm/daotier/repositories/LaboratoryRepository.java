/**
 * 
 */
package com.mfsi.hm.daotier.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mfsi.hm.daotier.models.Laboratory;

/**
 * @author shah
 *
 */
@Repository
public interface LaboratoryRepository extends JpaRepository<Laboratory, Long> {

}
