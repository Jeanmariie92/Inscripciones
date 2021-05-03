package com.jeanReb.appSpring.Exceptions;

public class SubjectOrIdNotFound extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1713941742579724467L;

	
	public SubjectOrIdNotFound() {
		super("Mteria o  Id no encontrado");
	}
	
	public SubjectOrIdNotFound(String message) {
		super(message);
		
	}
}
