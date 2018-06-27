/**
 * 
 */
package com.mfsi.hm.biztier.vos;

/**
 * @author shah
 *
 */
public enum RoleAccessLevel {
	ADMIN("admin", 1),
	HEAD("head", 2),
	DOCTOR("doctor", 3),
	PATIENT("patient", 4);
	
	private String role;
	private Integer accessLevel;
	
	private RoleAccessLevel(String role, Integer accessLevel) {
		this.role = role;
		this.accessLevel = accessLevel;
	}
	
	public Integer getAccessLevel(){
		return this.accessLevel;
	}
	
	public String getRole(){
		return this.role;
	}
	
	public static Integer getAccessLevelFromRoleName(String role){
		return RoleAccessLevel.valueOf(role).getAccessLevel();
	}
	
	@Override
	public String toString(){
		return "{ " + this.role + ", " + this.accessLevel + " }";
	}
}
