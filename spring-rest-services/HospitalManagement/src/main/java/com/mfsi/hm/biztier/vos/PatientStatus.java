/**
 * 
 */
package com.mfsi.hm.biztier.vos;

/**
 * @author shah
 *
 */
public enum PatientStatus {
	
	REGISTERED("REGISTER"),
	OPD_PATIENT("OPD_PATIENT"),
	ADMITTED("ADMITTED"),
	BILL_PENDING("BILL_PENDING"),
	DUES_CLEARANCE("DUES_CLEARANCE"),
	RELIEVED("RELIEVED");
	
	private String patientStatus;

	private PatientStatus(String patientStatus) {
		this.patientStatus = patientStatus;
	}
	
	public String getStatus(){
		return patientStatus;
	}
	
	@Override
	public String toString(){
		return patientStatus;
	}
}
