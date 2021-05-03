package com.jeanReb.appSpring.Exceptions;

public class TeacherOrIdNotFound extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1872526023340000548L;

	
	public TeacherOrIdNotFound() {
		super("Profesor o Id no encontrado");
	}
	
	public TeacherOrIdNotFound(String message) {
		super(message);
		
	}
}
