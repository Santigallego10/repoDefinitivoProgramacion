package com.cafeteria2.api.exceptions;

public class ClienteException extends Exception  {
	
	private static final long serialVersionUID = 1L;

	public ClienteException(String mensaje) {
		super(mensaje);
	}

}
