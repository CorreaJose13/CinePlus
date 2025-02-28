package com.correajose.cineplus.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private boolean ok;
    private int error_code;
    private String description;
    private StackTraceElement[] details;

    public ErrorResponse(boolean ok, int status, String message, StackTraceElement[] details) {
        this.ok = ok;
        this.error_code = status;
        this.description = message;
        this.details = details;
    }
}