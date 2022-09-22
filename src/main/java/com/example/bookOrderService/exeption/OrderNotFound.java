package com.example.bookOrderService.exeption;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class OrderNotFound extends RuntimeException {

    private long errorCode;
    private String statusMessage;

    public OrderNotFound(long errorCode, String statusMessage) {
        super(statusMessage);
        this.errorCode = errorCode;
        this.statusMessage = statusMessage;
    }
}
