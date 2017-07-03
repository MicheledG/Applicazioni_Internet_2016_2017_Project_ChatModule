package it.polito.ai.chat.exception;

import org.springframework.security.core.AuthenticationException;

public class FailedToResolveUsernameException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FailedToResolveUsernameException(String msg) {
		super(msg);
	}

}
