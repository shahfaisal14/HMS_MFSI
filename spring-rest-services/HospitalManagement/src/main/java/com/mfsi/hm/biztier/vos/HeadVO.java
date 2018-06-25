/**
 * 
 */
package com.mfsi.hm.biztier.vos;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shah
 *
 */
@Data
@NoArgsConstructor
public class HeadVO extends UserVO {

	private static final long serialVersionUID = 7091526910525958147L;
	
	private HospitalVO hospital;
	
	public HeadVO(Long dataStoreId, String userId, String firstName, String middleName, String lastName,
			String email, Date dateOfBirth) {
		super(dataStoreId, userId, firstName, middleName, lastName, email, dateOfBirth, new RoleVO("head", "Head"));
	}
}
