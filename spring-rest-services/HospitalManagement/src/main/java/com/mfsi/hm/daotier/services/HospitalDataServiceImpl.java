/**
 * 
 */
package com.mfsi.hm.daotier.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfsi.hm.biztier.vos.HospitalVO;
import com.mfsi.hm.core.util.SystemUtil;
import com.mfsi.hm.daotier.models.Hospital;
import com.mfsi.hm.daotier.repositories.HospitalRepository;

/**
 * @author shah
 *
 */
@Service("hospitalDataService")
public class HospitalDataServiceImpl implements HospitalDataService {

	@Autowired
	private HospitalRepository hospitalRepository;

	@Override
	public Hospital addHospital(Hospital hospital) {
		if(hospital != null){
			hospitalRepository.save(hospital);
			return hospital;
		}
		return null;
	}
	
	@Override
	public Hospital findByName(String name){
		Hospital hospital = null;
		if(StringUtils.isNotBlank(name)){
			hospital = hospitalRepository.findByName(name);
		}
		return hospital;
	}
}
