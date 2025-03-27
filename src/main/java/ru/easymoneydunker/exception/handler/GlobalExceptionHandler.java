package ru.easymoneydunker.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.validation.ConstraintViolationException;
import ru.easymoneydunker.exception.model.notfound.EatingNotFoundException;
import ru.easymoneydunker.exception.model.notfound.MealNotFoundException;
import ru.easymoneydunker.exception.model.notfound.UserNotFoundException;
import ru.easymoneydunker.exception.model.record.ExceptionRecord;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EatingNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionRecord> handleEatingNotFoundException(EatingNotFoundException ex) {
        ExceptionRecord record = new ExceptionRecord(
                ex.getMessage(),
                "The eating record with the given ID was not found.",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(record, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MealNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionRecord> handleMealNotFoundException(MealNotFoundException ex) {
        ExceptionRecord record = new ExceptionRecord(
                ex.getMessage(),
                "The meal with the given name was not found.",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(record, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionRecord> handleUserNotFoundException(UserNotFoundException ex) {
        ExceptionRecord record = new ExceptionRecord(
                ex.getMessage(),
                "The user with the given ID was not found.",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(record, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionRecord> handleConstraintViolationException(ConstraintViolationException ex) {
        String violationMessages = ex.getConstraintViolations().stream()
                .map(violation -> violation.getMessage())
                .collect(Collectors.joining(", "));

        ExceptionRecord record = new ExceptionRecord(
                "Validation failed for the request.",
                violationMessages,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(record, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionRecord> handleGlobalException(Exception ex) {
        ExceptionRecord record = new ExceptionRecord(
                "An unexpected error occurred.",
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(record, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
