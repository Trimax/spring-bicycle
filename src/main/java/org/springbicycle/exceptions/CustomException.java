package org.springbicycle.exceptions;

import org.springframework.http.HttpStatus;

public final class CustomException extends AbstractException {
    public CustomException(final String message) {
        super(message);
    }

    public CustomException(final String message, final Throwable exception) {
        super(message, exception);
    }

    @Override
    public final HttpStatus getCode() {
        return HttpStatus.FORBIDDEN;
    }
}
