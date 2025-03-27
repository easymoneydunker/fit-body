package ru.easymoneydunker.exception.model.record;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ExceptionRecord {
    private String message;
    private String details;
    private LocalDateTime timestamp;
}
