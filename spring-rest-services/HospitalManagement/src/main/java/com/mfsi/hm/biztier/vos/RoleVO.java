package com.mfsi.hm.biztier.vos;

import java.io.Serializable;

import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleVO implements Serializable {

	@Transient
	private static final long serialVersionUID = -7612557028680210882L;
	
	private String id;
	
	private String name;

}
