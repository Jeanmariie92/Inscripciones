package com.jeanReb.appSpring.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Productos implements Serializable {


	private static final long serialVersionUID = -7386746375238136802L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native",strategy="native")
	
	private Long idproductos;
	@Column
	private String nombre;
	@Column
	private int precio;
	
	public Long getIdproductos() {
		return idproductos;
	}
	public void setIdproductos(Long idproductos) {
		this.idproductos = idproductos;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idproductos == null) ? 0 : idproductos.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + precio;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Productos other = (Productos) obj;
		if (idproductos == null) {
			if (other.idproductos != null)
				return false;
		} else if (!idproductos.equals(other.idproductos))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (precio != other.precio)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Productos [idproductos=" + idproductos + ", nombre=" + nombre + ", precio=" + precio + "]";
	}
	
	
	
	
	
}
