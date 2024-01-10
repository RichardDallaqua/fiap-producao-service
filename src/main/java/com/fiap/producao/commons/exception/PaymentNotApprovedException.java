package com.fiap.producao.commons.exception;

public class PaymentNotApprovedException extends RuntimeException {

    public PaymentNotApprovedException(String message) {
        super(message);
    }
}
