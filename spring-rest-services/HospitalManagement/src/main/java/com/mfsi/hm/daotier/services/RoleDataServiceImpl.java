/**
 * 
 */
package com.mfsi.hm.daotier.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfsi.hm.daotier.models.Role;
import com.mfsi.hm.daotier.repositories.RoleRepository;

/**
 * @author shah
 *
 */
@Service("roleDataService")
public class RoleDataServiceImpl implements RoleDataService {

	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public Role getRoleById(String roleId) {
		Role role = null;
		if(StringUtils.isNotBlank(roleId)){
			role = roleRepository.findById(roleId);
		}
		return role;
	}

}
