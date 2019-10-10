package com.github.ankurpathak.isobarwallet.controller.rest;

import com.github.ankurpathak.isobarwallet.exception.InvalidException;
import com.github.ankurpathak.isobarwallet.exception.NotFoundException;
import com.github.ankurpathak.isobarwallet.exception.ValidationException;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);


    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<?> handleValidationException(ValidationException ex, WebRequest request) {
        log.error("{} message: {} cause: {}",ex.getClass().getSimpleName(),  ex.getMessage(), ex.getCause());
        log.error(ExceptionUtils.getStackTrace(ex));
        return handleExceptionInternal(
                ex,
                null,
                new HttpHeaders(),
                HttpStatus.CONFLICT,
                request
        );
    }


    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<?> handleNotFoundException(NotFoundException ex, WebRequest request) {
        log.error("{} message: {} cause: {}",ex.getClass().getSimpleName(),  ex.getMessage(), ex.getCause());
        log.error(ExceptionUtils.getStackTrace(ex));
        return handleExceptionInternal(
                ex,
                null,
                new HttpHeaders(),
                HttpStatus.NOT_FOUND,
                request
        );
    }


    @ExceptionHandler({InvalidException.class})
    public ResponseEntity<?> handleInvalidException(InvalidException ex, WebRequest request) {
        log.error("{} message: {} cause: {}",ex.getClass().getSimpleName(),  ex.getMessage(), ex.getCause());
        log.error(ExceptionUtils.getStackTrace(ex));
        return handleExceptionInternal(
                ex,
                null,
                new HttpHeaders(),
                HttpStatus.CONFLICT,
                request
        );
    }


    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("{} message: {} cause: {}",ex.getClass().getSimpleName(),  ex.getMessage(), ex.getCause());
        log.error(ExceptionUtils.getStackTrace(ex));
        return super.handleTypeMismatch(ex, headers, HttpStatus.CONFLICT, request);
    }
}
