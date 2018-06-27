/**
 * 
 */
package com.mfsi.hm.core.responses;

/**
 * @author shah
 *
 */
public enum ResponseType {
	
	ERROR("ERROR"),
	WARN("WARN"),
	INFO("INFO"),
	SUCCESS("SUCCESS");
	
	private String responseType;
	
	private ResponseType(String responseType) {
		this.responseType = responseType;
	}
	
	public String getType(){
		return this.responseType;
	}
	
	@Override
	public String toString(){
		return this.responseType;
	}
}
