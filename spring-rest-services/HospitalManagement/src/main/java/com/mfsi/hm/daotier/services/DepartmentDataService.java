/**
 * 
 */
package com.mfsi.hm.daotier.services;

import java.util.Set;

import com.mfsi.hm.daotier.models.Department;

/**
 * @author shah
 *
 */
public interface DepartmentDataService {
	
	public Department createDepartment(Department department);

	public Set<Department> createDepartments(Set<Department> departments);
}
