/**
 * 
 */
package com.mfsi.hm.biztier.services;

import com.mfsi.hm.biztier.vos.HospitalVO;
import com.mfsi.hm.biztier.vos.UserVO;
import com.mfsi.hm.core.responses.BizResponseVO;

/**
 * @author shah
 *
 */
public interface HospitalService {

	public BizResponseVO addHospital(HospitalVO hospitalVO, UserVO loggedInUser);

	public BizResponseVO getHospitalsList();

	public BizResponseVO getHospitalById(Long dataStoreId);
	
}
