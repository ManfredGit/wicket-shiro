package com.onassignment.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.onassignment.model.PersistentEntity;

@Entity
@Table(name="Permissions")
public class Permission extends PersistentEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String domain;
	private String action;
	private Set<Role> roles = new HashSet<Role>(0);
	
	public String getDomain() {
		return domain;
	}
	
	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "permissions")
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
}
