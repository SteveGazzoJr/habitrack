package com.projectdave.habitrack.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String cause) {
        super(cause);
    }
}
