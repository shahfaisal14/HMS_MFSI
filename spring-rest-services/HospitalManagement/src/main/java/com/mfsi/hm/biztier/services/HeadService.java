/**
 * 
 */
package com.mfsi.hm.biztier.services;

import com.mfsi.hm.biztier.vos.HeadVO;
import com.mfsi.hm.biztier.vos.UserVO;
import com.mfsi.hm.core.responses.BizResponseVO;

/**
 * @author shah
 *
 */
public interface HeadService {
	
	public BizResponseVO createHead(HeadVO headVO, UserVO loggedInUser);
}
