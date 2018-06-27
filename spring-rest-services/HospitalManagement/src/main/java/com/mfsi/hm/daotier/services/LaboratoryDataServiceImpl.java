/**
 * 
 */
package com.mfsi.hm.daotier.services;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfsi.hm.daotier.models.Laboratory;
import com.mfsi.hm.daotier.repositories.LaboratoryRepository;

/**
 * @author shah
 *
 */
@Service("laboratoryDataService")
public class LaboratoryDataServiceImpl implements LaboratoryDataService {

	@Autowired
	private LaboratoryRepository laboratoryRepository;
	
	@Override
	public Laboratory createLaboratory(Laboratory laboratory) {
		Laboratory newLab = null;
		if(laboratory != null){
			newLab = laboratoryRepository.save(laboratory);
		}
		return newLab;
	}
	
	@Override
	public Set<Laboratory> createLaboratories(Set<Laboratory> laboratories){
		Set<Laboratory> newLabs = null;
		if(laboratories != null){
			newLabs = laboratoryRepository.saveAll(laboratories).stream().collect(Collectors.toSet());
		}
		return newLabs;
	}

}
