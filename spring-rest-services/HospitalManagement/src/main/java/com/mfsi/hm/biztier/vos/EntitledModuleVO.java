/**
 * 
 */
package com.mfsi.hm.biztier.vos;

import java.io.Serializable;

import lombok.Data;

/**
 * @author shah
 *
 */
@Data
public class EntitledModuleVO implements Serializable {

	private static final long serialVersionUID = 7284642301088549739L;
	
	private String moduleId;
	
	private String name;
	
	private String visibleName;
	
	private String relativeUrl;
	
	private Integer orderNumber;
		
	private String imageUrl;
	
	private String description;
	
	private Boolean canCreate;
	
	private Boolean canDelete;
	
	private Boolean canUpdate;
	
	private Boolean canRead;
}
