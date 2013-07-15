package com.onassignment.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public abstract class PersistentEntity {
	private Long id;
	private List<String> validationFlags = new ArrayList<String>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@Transient
	public List<String> getValidationFlags() {
		return validationFlags;
	}

	@Transient
	public boolean isUnsavedRecord() {
		Long id = null;
		
		try {
			id = Long.valueOf(getId());
		} catch (NullPointerException e) {
			// id is currently null
		}
		
		return (id != null) ? false : true;
	}
	
}
