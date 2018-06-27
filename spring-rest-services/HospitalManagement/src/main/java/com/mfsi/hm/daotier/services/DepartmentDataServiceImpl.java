/**
 * 
 */
package com.mfsi.hm.daotier.services;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfsi.hm.daotier.models.Department;
import com.mfsi.hm.daotier.repositories.DepartmentRepository;

/**
 * @author shah
 *
 */
@Service("departmentDataService")
public class DepartmentDataServiceImpl implements DepartmentDataService {

	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Override
	public Department createDepartment(Department department) {
		Department newDepartment = new Department();
		if(department != null){
			newDepartment = departmentRepository.save(department);
		}
		return newDepartment;
	}
	
	@Override
	public Set<Department> createDepartments(Set<Department> departments){
		Set<Department> newDepartments = null;
		
		if(departments != null){
			newDepartments = departmentRepository.saveAll(departments).stream().collect(Collectors.toSet());
		}
		
		return newDepartments;
	}

}
