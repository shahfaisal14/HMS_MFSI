/**
 * 
 */
package com.mfsi.hm.daotier.services;

import java.util.Set;

import com.mfsi.hm.daotier.models.Laboratory;

/**
 * @author shah
 *
 */
public interface LaboratoryDataService {

	public Laboratory createLaboratory(Laboratory laboratory);

	public Set<Laboratory> createLaboratories(Set<Laboratory> laboratories);
}
