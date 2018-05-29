package com.mfsi.hm.biztier.vos;

import java.io.Serializable;

import javax.persistence.Transient;

import lombok.Data;

@Data
public class RoleVO implements Serializable {

	@Transient
	private static final long serialVersionUID = -7612557028680210882L;
	
	private String id;
	
	private String name;

}
