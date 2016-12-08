package org.springbicycle.exceptions;

import org.springframework.http.HttpStatus;

public abstract class AbstractException extends RuntimeException {
    AbstractException(final String message) {
        super(message);
    }

    AbstractException(final String message, final Throwable exception) {
        super(message, exception);
    }

    public abstract HttpStatus getCode();
}
