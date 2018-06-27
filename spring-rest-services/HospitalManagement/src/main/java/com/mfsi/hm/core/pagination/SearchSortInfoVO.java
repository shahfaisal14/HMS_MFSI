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
public class SearchSortInfoVO implements Serializable {
	
	public static final String MODIFIED_START_DATE = "modifiedStartDate";
	public static final String MODIFIED_END_DATE = "modifiedEndDate";
	public static final String CREATED_START_DATE = "createdStartDate";
	public static final String CREATED_END_DATE = "createdEndDate";

	@Transient
	private static final long serialVersionUID = 1256013599580445277L;
	
	private String fieldName;
	
	private List<Object> searchedValues;
	
	private String sortingOrder;
	
	private String filterValue;
	
}
