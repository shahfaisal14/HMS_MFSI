package com.mfsi.hm.core.responses;

import java.io.Serializable;

import lombok.Data;

@Data
public class BizResponseVO implements Serializable {

	private static final long serialVersionUID = 2836945700853933177L;
	
	private ResponseType responseType;
	
	private String message;
	
	private Object responseData;
	
	private Object errorDescription;
	
	private String errorStackTrace;
	
	private String errorCode;

}
