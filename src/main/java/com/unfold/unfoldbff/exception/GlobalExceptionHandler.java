package com.unfold.unfoldbff.exception;

import com.unfold.unfoldbff.model.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDto> handleException(RuntimeException e) {
        ErrorResponseDto response = new ErrorResponseDto("R001", "Internal Server Error",
                HttpStatus.INTERNAL_SERVER_ERROR.value(), String.valueOf(e.hashCode()), e.toString());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleException(NotFoundException e) {
        logger.error("NotFoundException Occurred: " + e);
        ErrorResponseDto response = new ErrorResponseDto(e.getErrorCode(), e.getErrorMsg(), (e.getStatusCode().value()),
                String.valueOf(e.hashCode()), e.toString());
        return new ResponseEntity<>(response, e.getStatusCode());
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ErrorResponseDto> handleException(InvalidInputException e) {
        logger.error("NotFoundException Occurred: " + e);
        ErrorResponseDto response = new ErrorResponseDto(e.getErrorCode(), e.getErrorMsg(), (e.getStatusCode().value()),
                String.valueOf(e.hashCode()), e.toString());
        return new ResponseEntity<>(response, e.getStatusCode());
    }
}
