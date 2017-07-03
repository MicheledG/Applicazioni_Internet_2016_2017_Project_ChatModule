package it.polito.ai.chat.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import java.security.SignatureException;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
	
    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler(FailedToAuthenticateException.class)
    public void failedToAuthenticate() {
    }
    
    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler(FailedToResolveUsernameException.class)
    public void failedToResolveNickname() {
    }
    
    @ResponseStatus(FORBIDDEN)
    @ExceptionHandler(SignatureException.class)
    public void failedToVerify() {
        System.out.println("");
    }
    
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void failedToValidate() {
    }
    
}
