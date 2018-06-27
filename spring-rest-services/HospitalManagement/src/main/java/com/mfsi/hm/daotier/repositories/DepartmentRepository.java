/**
 * 
 */
package com.mfsi.hm.daotier.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mfsi.hm.daotier.models.Department;

/**
 * @author shah
 *
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
