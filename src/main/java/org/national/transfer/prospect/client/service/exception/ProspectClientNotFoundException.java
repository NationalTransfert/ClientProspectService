package org.national.transfer.prospect.client.service.exception;

import lombok.Getter;

public class ProspectClientNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    @Getter
    private Integer statusCode;

    public ProspectClientNotFoundException() {
        super();
    }

    public ProspectClientNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProspectClientNotFoundException(String message) {
        super(message);
    }

    public ProspectClientNotFoundException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
