package com.dominio.api.exception;

public class NegocioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NegocioException() {
		super();
	}

	public NegocioException(String message) {
		super(message);
	}

	public NegocioException(String message, Exception erro) {
		super(message, erro);
	}
}
