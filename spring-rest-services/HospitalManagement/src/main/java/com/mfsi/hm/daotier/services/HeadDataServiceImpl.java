/**
 * 
 */
package com.mfsi.hm.daotier.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfsi.hm.daotier.models.Head;
import com.mfsi.hm.daotier.repositories.HeadRepository;

/**
 * @author shah
 *
 */
@Service("headDataService")
public class HeadDataServiceImpl implements HeadDataService {

	@Autowired
	HeadRepository headRepository;
	
	@Override
	public Head createHead(Head head) {
		Head newHead = null;
		
		if(head != null){
			newHead = headRepository.save(head);
		}
		return newHead;
	}

}
