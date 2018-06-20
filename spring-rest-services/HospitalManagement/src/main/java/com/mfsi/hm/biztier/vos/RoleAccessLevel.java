/**
 * 
 */
package com.mfsi.hm.biztier.vos;

/**
 * @author shah
 *
 */
public enum RoleAccessLevel {
	ADMIN("admin", "1"),
	HOSPITAL_HEAD("hospitalHead", "2"),
	DOCTOR("doctor", "3"),
	PATIENT("patient", "4");
	
	private String accessLevel;
	private String role;
	
	private RoleAccessLevel(String role, String accessLevel) {
		this.role = role;
		this.accessLevel = accessLevel;
	}
	
	public String getAccessLevel(){
		return this.accessLevel;
	}
	
	public String getRole(){
		return this.role;
	}
	
	public static String getAccessLevelFromRoleName(String role){
		return RoleAccessLevel.valueOf(role).getAccessLevel();
	}
	
	@Override
	public String toString(){
		return "{ " + this.role + ", " + this.accessLevel + " }";
	}
}
