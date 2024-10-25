package org.example.cart.Exception;

import lombok.extern.slf4j.Slf4j;
import org.example.cart.Exception.Cart.CartNotFoundException;
import org.example.cart.Exception.Cart.MissingUserIdException;
import org.example.cart.Exception.Product.Car.ExternalServiceException;
import org.example.cart.Utility.ConsoleColor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    // General Exceptions
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Void> handleNoResourceFound(NoResourceFoundException ex) {
        if (ex.getMessage().contains("favicon.ico")) {
            log.warn("Favicon not found, but ignoring this error");
            return ResponseEntity.notFound().build();
        }
        log.error("Resource not found: ", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception exception) {
        ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An unexpected error occurred");
        log.error(ConsoleColor.Red(exception.getMessage()), exception);

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ErrorResponse> handleInvalidData(Exception exception) {
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        log.warn(ConsoleColor.Yellow(exception.getMessage()));

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Exceptions related to Cart Controller
    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCartDoesNotExist(Exception exception) {
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        log.warn(ConsoleColor.Yellow(exception.getMessage()));

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Exceptions related to Product Controller
    @ExceptionHandler(ExternalServiceException.class)
    public ResponseEntity<ErrorResponse> handleExternalServiceException(ExternalServiceException exception) {
        ErrorResponse error = new ErrorResponse(HttpStatus.SERVICE_UNAVAILABLE.value(), exception.getMessage());

        return new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
