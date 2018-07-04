/**
 * 
 */
package com.mfsi.hm.biztier.vos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Transient;

import lombok.Data;

/**
 * @author shah
 *
 */
@Data
public class LoginSuccessVO implements Serializable {
	
	private static final long serialVersionUID = -2358093123264461528L;
	
	private String message;
	
	private String userName;
	
	private String userCode;
	
	private String userRole;
	
	private String authToken;
		
	private List<EntitledModuleVO> entitledModules;

}
