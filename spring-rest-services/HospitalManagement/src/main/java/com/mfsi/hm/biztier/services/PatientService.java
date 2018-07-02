/**
 * 
 */
package com.mfsi.hm.biztier.services;

import com.mfsi.hm.biztier.vos.PatientVO;
import com.mfsi.hm.core.responses.BizResponseVO;

/**
 * @author shah
 *
 */
public interface PatientService {
	
	public BizResponseVO registerPatient(PatientVO patientVO);
}
