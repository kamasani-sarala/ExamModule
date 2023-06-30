
package com.demo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(UserControllerAdvice.class);

    @ExceptionHandler(ExamNotFoundException.class)
    public ResponseEntity<String> handleExamNotFoundException(ExamNotFoundException exception) {
        String message = exception.getMessage();
        logger.error("ExamNotFoundException occurred: {}", message);
        return new ResponseEntity<>(message, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(InvalidExamIdException.class)
    public ResponseEntity<String> handleInvalidExamIdException(InvalidExamIdException exception) {
        String message = exception.getMessage();
        logger.error("InvalidExamIdException occurred: {}", message);
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullValuesFoundException.class)
    public ResponseEntity<String> handleNullValuesFoundException(NullValuesFoundException exception) {
        String message = exception.getMessage();
        logger.error("NullValuesFoundException occurred: {}", message);
        return new ResponseEntity<>(message, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<String> handleNumberFormatException(NumberFormatException exception) {
        String message = exception.getMessage();
        logger.error("NumberFormatException occurred: {}", message);
        return new ResponseEntity<>(message, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(NullUserFoundException.class)
    public ResponseEntity<String> handleNullUserFoundException(NullUserFoundException exception) {
        String message = exception.getMessage();
        logger.error("NullUserFoundException occurred: {}", message);
        return new ResponseEntity<>(message, HttpStatus.NOT_ACCEPTABLE);
    }
}
