package com.oregano.oreganoERP.exceptions;

public class InvalidLoginException extends Exception {


	private static final long serialVersionUID = 1L;
	
	public InvalidLoginException() {
		super("Login Inválido. Por favor tente novamente.");
	}

}
