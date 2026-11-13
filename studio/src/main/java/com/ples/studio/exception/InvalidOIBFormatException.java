package com.ples.studio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Custom exception for invalid OIB format
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidOIBFormatException extends RuntimeException {
    public InvalidOIBFormatException(String message) {
        super(message + " Neispravan format OIB-a ");
    }
}
