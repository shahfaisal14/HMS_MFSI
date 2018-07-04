/**
 * 
 */
package com.mfsi.hm.biztier.vos;

import java.io.Serializable;
import java.util.Set;

import lombok.Data;

/**
 * @author shah
 *
 */
@Data
public class DoctorsHospitalwiseVO implements Serializable {

	private static final long serialVersionUID = -5398495684379451887L;
	
	private HospitalVO hospital;
	
	private Set<DoctorVO> doctors;

}
