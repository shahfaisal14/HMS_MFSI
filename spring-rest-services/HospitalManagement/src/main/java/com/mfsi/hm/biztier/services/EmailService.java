/**
 * 
 */
package com.mfsi.hm.biztier.services;

/**
 * @author shah
 *
 */
public interface EmailService {
	public Boolean sendEMail(String[] toEmails, String subject, String body);
}
