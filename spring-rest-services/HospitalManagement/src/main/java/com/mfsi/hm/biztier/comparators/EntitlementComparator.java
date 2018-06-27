/**
 * 
 */
package com.mfsi.hm.biztier.comparators;

import java.util.Comparator;

import com.mfsi.hm.biztier.vos.EntitledModuleVO;

/**
 * @author shah
 *
 */
public class EntitlementComparator implements Comparator<EntitledModuleVO>{
	
	@Override
	public int compare(EntitledModuleVO o1, EntitledModuleVO o2) {
		return o1.getOrderNumber().compareTo(o2.getOrderNumber());
	}
}
