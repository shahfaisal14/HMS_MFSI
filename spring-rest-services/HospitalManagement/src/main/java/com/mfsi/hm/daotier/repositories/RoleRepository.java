/**
 * 
 */
package com.mfsi.hm.daotier.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mfsi.hm.daotier.models.Role;

/**
 * @author shah
 *
 */
public interface RoleRepository extends CrudRepository<Role, Long> {
	
	public Role findById(String roleId);
}
