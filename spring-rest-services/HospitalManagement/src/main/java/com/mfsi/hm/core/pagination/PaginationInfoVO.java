/**
 * 
 */
package com.mfsi.hm.core.pagination;

import java.io.Serializable;

import javax.persistence.Transient;

import lombok.Data;

/**
 * @author shah
 *
 */
@Data
public class PaginationInfoVO implements Serializable {

	@Transient
	private static final long serialVersionUID = 1061240016838748224L;
	
	private Integer totalItems;
	
	private Integer currentPage;
	
	private Integer itemPerPage;
	
	private Integer maxSize;
	
	private Boolean isTotalCountRequired;

}
