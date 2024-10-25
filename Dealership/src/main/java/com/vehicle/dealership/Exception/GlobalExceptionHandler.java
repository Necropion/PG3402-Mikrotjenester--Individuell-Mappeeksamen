package com.vehicle.dealership.Exception;

import com.vehicle.dealership.Utility.ConsoleColor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    // Unexpected Errors
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Void> handleNoResourceFound(NoResourceFoundException ex) {
        if (ex.getMessage().contains("favicon.ico")) {
            log.warn(ex.getClass().getSimpleName() + ": " + "Favicon not found, but ignoring this error");
            return ResponseEntity.notFound().build();
        }
        log.error("Resource not found: ", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception exception) {
        ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An unexpected error occurred");
        log.error(ConsoleColor.Red(exception.getClass().getSimpleName() + ": " + exception.getMessage()), exception);

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Item by ID Not Found
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleItemNotFoundException(ItemNotFoundException exception) {
        ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());
        log.warn(ConsoleColor.Yellow(exception.getClass().getSimpleName() + ": " + exception.getMessage()));

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Item Product ID Already Exists
    @ExceptionHandler(ItemProductIDExistsException.class)
    public ResponseEntity<ErrorResponse> handleItemProductIDExists(ItemProductIDExistsException exception) {
        ErrorResponse error = new ErrorResponse(HttpStatus.CONFLICT.value(), exception.getMessage());
        log.warn(ConsoleColor.Yellow(exception.getClass().getSimpleName() + ": " + exception.getMessage()));

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    // Invalid POST Data
    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ErrorResponse> handleInvalidDataException(InvalidDataException exception) {
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        log.warn(ConsoleColor.Yellow(exception.getClass().getSimpleName() + ": " + exception.getMessage()));

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // External Event Issues
    @ExceptionHandler(EventProcessingException.class)
    public ResponseEntity<ErrorResponse> handleEventProcessingException(EventProcessingException exception) {
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        log.warn(ConsoleColor.Yellow(exception.getClass().getSimpleName() + ": " + exception.getMessage()));

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
