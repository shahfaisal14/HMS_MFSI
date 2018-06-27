/**
 * 
 */
package com.mfsi.hm.biztier.vos;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * @author shah
 *
 */
@Data
public class AppointmentVO implements Serializable {

	private static final long serialVersionUID = -2783602483935937257L;
	
	private DoctorVO doctor;
	
	private PatientVO patient;
	
	private LocalDateTime date;

}
