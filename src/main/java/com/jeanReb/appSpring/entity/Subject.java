package com.jeanReb.appSpring.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;


import org.hibernate.annotations.GenericGenerator;

@Entity
public class Subject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8466746155330468906L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native",strategy="native")
	private Long id;
	
	@Column 
	private String name;
	
	@Column 
	private int quota_max;

	
	@ManyToOne(fetch = FetchType.LAZY)
	
	private Teacher teacher;
	
	public Subject() {
		
		
	}
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuota_max() {
		return quota_max;
	}

	public void setQuota_max(int quota_max) {
		this.quota_max = quota_max;
	}

	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + ", quota_max=" + quota_max + "]";
	}
	
	
}
