/**
 * 
 */
package com.mfsi.hm.core.pagination;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Transient;

import lombok.Data;

/**
 * @author shah
 *
 */
@Data
public class FilterInfoVO implements Serializable {

	@Transient
	private static final long serialVersionUID = -2953411772728840332L;
	
	private List<String> searchParamsList;
	
	private PaginationInfoVO paginationInfoVO;
	
	private List<SearchSortInfoVO> searchSortInfoVOs;
	
	private Object result;
	
}
