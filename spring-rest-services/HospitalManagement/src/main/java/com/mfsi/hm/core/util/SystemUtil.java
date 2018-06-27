/**
 * 
 */
package com.mfsi.hm.core.util;

import java.util.Date;

import com.mfsi.hm.core.common.BaseDataModel;
import static com.mfsi.hm.core.common.Constants.SYSTEM_OF_RECORDX_SYSTEM;

/**
 * @author shah
 *
 */
public class SystemUtil {
	
	public static final Boolean isBlankOrNull(String str) {
		Boolean result = false;
		if(str == null || "".equals(str.trim())) {
			result = true;
		}
		return result;
	}

	/**
	 * 
	 * @param baseDataModel
	 * @param loggedInUserId
	 * @param sysemOfRecordX
	 */
	public static final void setBaseModelValues(BaseDataModel baseDataModel, String loggedInUserId, String sysemOfRecordX) {
		if(baseDataModel != null) {
			if(baseDataModel.getDataStoreId() == null) {
				baseDataModel.setCreatedBy(loggedInUserId);
				baseDataModel.setCreatedDate(new Date());
			}			
			baseDataModel.setModifiedBy(loggedInUserId);
			baseDataModel.setModifiedDate(new Date());
			if(SystemUtil.isBlankOrNull(sysemOfRecordX)) {
				baseDataModel.setSystemOfRecordX(sysemOfRecordX);
			} else {
				baseDataModel.setSystemOfRecordX(SYSTEM_OF_RECORDX_SYSTEM);
			}
		}
	}
}
