package com.davi.restaurant_burguer.exceptions.handler;

import com.davi.restaurant_burguer.exceptions.ExceptionResponse;
import com.davi.restaurant_burguer.exceptions.InvalidTypeException;
import com.davi.restaurant_burguer.exceptions.NotfoundException;
import com.davi.restaurant_burguer.exceptions.FileSizeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleAllExceptions(Exception e, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(),new Date());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionResponse> handleNullPointerException(NullPointerException e, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage(), HttpStatus.NOT_FOUND.value(),new Date());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotfoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotfoundException(NotfoundException e, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage(), HttpStatus.NOT_FOUND.value(),new Date());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FileSizeException.class)
    public ResponseEntity<ExceptionResponse> handleStorageException(FileSizeException e, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage(), HttpStatus.PAYLOAD_TOO_LARGE.value(),new Date());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.PAYLOAD_TOO_LARGE);
    }

    @ExceptionHandler(InvalidTypeException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidTypeException(InvalidTypeException e, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value(),new Date());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
