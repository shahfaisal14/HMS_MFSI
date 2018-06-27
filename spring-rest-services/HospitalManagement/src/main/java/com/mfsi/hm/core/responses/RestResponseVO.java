package com.mfsi.hm.core.responses;

import java.io.Serializable;

import lombok.Data;

@Data
public class RestResponseVO implements Serializable {

	private static final long serialVersionUID = -6870328564132614667L;
	
	private ResponseType responseType;
	
	private String message;
	
	private Object responseData;
	
	private Object errorDescription;
	
	private String errorStackTrace;
	
	private String errorCode;

}
