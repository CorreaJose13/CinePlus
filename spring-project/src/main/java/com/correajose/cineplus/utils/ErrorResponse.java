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

    public ErrorResponse(boolean ok, int status, String message) {
        this.ok = ok;
        this.error_code = status;
        this.description = message;
    }
}