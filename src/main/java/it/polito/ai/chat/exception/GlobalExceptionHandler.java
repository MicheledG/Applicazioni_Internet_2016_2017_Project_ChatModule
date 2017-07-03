package it.polito.ai.chat.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.SignatureException;

import static org.springframework.http.HttpStatus.*;

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

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(UnknownTopic.class)
    public void unknownTopic() {
    }

}
