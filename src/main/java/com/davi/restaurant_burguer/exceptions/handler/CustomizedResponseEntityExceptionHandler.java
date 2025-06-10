package com.davi.restaurant_burguer.exceptions.handler;

import com.davi.restaurant_burguer.exceptions.ExceptionResponse;
import com.davi.restaurant_burguer.exceptions.InvalidTypeException;
import com.davi.restaurant_burguer.exceptions.NotfoundException;
import com.davi.restaurant_burguer.exceptions.FileSizeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import software.amazon.awssdk.services.sns.model.SnsException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler{

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleAllExceptions(Exception e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(),new Date());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionResponse> handleNullPointerException(NullPointerException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage(), HttpStatus.NOT_FOUND.value(),new Date());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotfoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotfoundException(NotfoundException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage(), HttpStatus.NOT_FOUND.value(),new Date());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FileSizeException.class)
    public ResponseEntity<ExceptionResponse> handleStorageException(FileSizeException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage(), HttpStatus.PAYLOAD_TOO_LARGE.value(),new Date());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.PAYLOAD_TOO_LARGE);
    }

    @ExceptionHandler(InvalidTypeException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidTypeException(InvalidTypeException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value(),new Date());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SnsException.class)
    public ResponseEntity<ExceptionResponse> handleSnsException(SnsException e){
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(field, message);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
