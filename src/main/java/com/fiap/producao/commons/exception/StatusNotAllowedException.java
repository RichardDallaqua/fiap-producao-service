package com.fiap.producao.commons.exception;

public class StatusNotAllowedException extends RuntimeException {
    public StatusNotAllowedException(String message) {
        super(message);
    }
}
