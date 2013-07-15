package com.onassignment.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.onassignment.model.PersistentEntity;
import com.onassignment.model.User;

@Entity
@Table(name="Roles")
public class Role extends PersistentEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String role;
	private boolean active;
	private Set<Permission> permissions = new HashSet<Permission>(0);
	private Set<User> users = new HashSet<User>(0);
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "Roles_Permissions", 
		joinColumns = {@JoinColumn(name="ROLE_ID")},
		inverseJoinColumns = {@JoinColumn(name="PERMISSION_ID")})
	public Set<Permission> getPermissions() {
		return permissions;
	}
	
	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
}
