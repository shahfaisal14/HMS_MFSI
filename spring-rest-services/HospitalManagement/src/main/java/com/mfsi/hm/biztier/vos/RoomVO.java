/**
 * 
 */
package com.mfsi.hm.biztier.vos;

import java.io.Serializable;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shah
 *
 */
@Data
@NoArgsConstructor
public class RoomVO implements Serializable {

	private static final long serialVersionUID = 3303078791703764622L;
	
	private Long dataStoreId;

	private String roomNumber;
	
	private Integer totalBeds;
	
	private Integer occupiedBeds;
	
	private Integer availableBeds;
	
	private Integer chargesPerDay;
	
	private HospitalVO hospital;
	
	private Set<PatientVO> patients;
}
