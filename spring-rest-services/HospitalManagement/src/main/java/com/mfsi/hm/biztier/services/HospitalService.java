/**
 * 
 */
package com.mfsi.hm.biztier.services;

import com.mfsi.hm.biztier.vos.HospitalVO;
import com.mfsi.hm.core.pagination.FilterInfoVO;
import com.mfsi.hm.core.responses.BizResponseVO;
import com.mfsi.hm.core.responses.RestResponseVO;

/**
 * @author shah
 *
 */
public interface HospitalService {

	public BizResponseVO addHospital(HospitalVO hospitalVO, String userId);

	public RestResponseVO getHospitalList(FilterInfoVO filterInfoVO);
	
}
