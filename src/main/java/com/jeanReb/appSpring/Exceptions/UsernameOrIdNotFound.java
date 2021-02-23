package com.jeanReb.appSpring.Exceptions;

public class UsernameOrIdNotFound extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2361724220851614168L;

	public UsernameOrIdNotFound() {
		super("Usuario o Id no encontrado");
	}
	
	public UsernameOrIdNotFound(String message) {
		super(message);
		
	}
}
