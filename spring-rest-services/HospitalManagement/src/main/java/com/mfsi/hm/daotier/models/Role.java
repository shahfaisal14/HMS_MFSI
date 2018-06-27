package com.mfsi.hm.daotier.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.mfsi.hm.core.common.BaseDataModel;

import lombok.Data;

@Entity
@Data
public class Role extends BaseDataModel {

	@Transient
	private static final long serialVersionUID = -361200315297152508L;

	@Column(name = "id", unique = true, nullable = false, length = 225)
	private String id;
	
	@Column(name = "name", unique = true, length = 1024)
	private String name;
	
	@Column(name = "description", nullable = true, length = 2048)
	private String description;
	
	@OneToMany(mappedBy="role")
	private Set<User> users = new HashSet<>();
	
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinTable(name="RoleModuleMapping",
		joinColumns = {@JoinColumn(name = "roles")},
		inverseJoinColumns = {@JoinColumn(name = "modules")})
	private Set<Module> modules;
	
	public Role() {}
	
	public Role(String createdBy, String modifiedBy, Date createdDate, Date modfiedDate, String systemOfRecordX, Long versionNumber) {
		super(createdBy, modifiedBy, createdDate, modfiedDate, systemOfRecordX, versionNumber);
	}
	
}
