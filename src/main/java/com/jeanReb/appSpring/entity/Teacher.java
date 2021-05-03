package com.jeanReb.appSpring.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import org.hibernate.annotations.GenericGenerator;

@Entity
public class Teacher implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -5753907710037001571L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native",strategy="native")
	private Long id;
	
	@Column 
	
	private String name;
	
	@Column 
	
	private String last_name;
	
	@Column 
	
	private int dni;
	
	
	
	public Teacher() {
		
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

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	

	@Override
	public String toString() {
		return   name + " " +   last_name  ;
	}
	
	
	
}
