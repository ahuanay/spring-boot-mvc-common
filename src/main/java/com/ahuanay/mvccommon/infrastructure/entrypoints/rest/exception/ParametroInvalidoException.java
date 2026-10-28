package com.ahuanay.mvccommon.infrastructure.entrypoints.rest.exception;

public class ParametroInvalidoException extends RuntimeException {
    public ParametroInvalidoException(String message) {
        super(message);
    }
}