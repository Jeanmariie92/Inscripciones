package com.jeanReb.appSpring.Exceptions;

public class CustomeFieldValidationException extends Exception{


	private static final long serialVersionUID = 5552361682115047405L;
		private String fieldName;
		
		public CustomeFieldValidationException(String message, String fieldName) {
			super(message);
			this.fieldName = fieldName;
		}
		
		public String getFieldName() {
			return fieldName;
		}
	}

