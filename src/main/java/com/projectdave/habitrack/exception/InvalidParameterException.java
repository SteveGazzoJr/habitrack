package com.projectdave.habitrack.exception;

public class InvalidParameterException extends RuntimeException {
    public InvalidParameterException(String cause) {
        super(cause);
    }
}
