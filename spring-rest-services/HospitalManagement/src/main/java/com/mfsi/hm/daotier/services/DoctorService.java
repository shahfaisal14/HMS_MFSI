/**
 * 
 */
package com.mfsi.hm.daotier.services;

import com.mfsi.hm.biztier.vos.DoctorVO;
import com.mfsi.hm.biztier.vos.UserVO;
import com.mfsi.hm.core.responses.BizResponseVO;

/**
 * @author shah
 *
 */
public interface DoctorService {
	
	public BizResponseVO createDoctor(DoctorVO doctorVO, UserVO loggedInUser);

	public BizResponseVO getDoctorsList();

}
