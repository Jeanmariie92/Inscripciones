package com.jeanReb.appSpring.Exceptions;

public class ProductOrIdNotFound extends Exception {



	/**
	 * 
	 */
	private static final long serialVersionUID = -3926824806885843598L;

	public ProductOrIdNotFound() {
		super("Producto o Id no encontrado");
	}
	
	public ProductOrIdNotFound(String message) {
		super(message);
		
	}
}
