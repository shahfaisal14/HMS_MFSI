/**
 * 
 */
package com.mfsi.hm.daotier.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfsi.hm.daotier.models.Email;
import com.mfsi.hm.daotier.repositories.EmailRepository;

/**
 * @author shah
 *
 */
@Service("emailDataService")
public class EmailDataServiceImpl implements EmailDataService {

	@Autowired
	private EmailRepository emailRepository;

	@Override
	public Email findActiveSMTP() {
		return emailRepository.findByIsActive(true);
	}

}
