package com.correajose.cineplus.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class ResponseUtils {

    private static ResponseEntity<ErrorResponse> generateErrorResponse(HttpStatus status, String message, StackTraceElement[] ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                false,
                status.value(),
                message,
                ex
        );
        return ResponseEntity.status(status).body(errorResponse);
    }

    public static ResponseEntity<ErrorResponse> buildErrorResponse(Class<?> clazz,HttpStatus status, String message, StackTraceElement[] ex) {
        LoggingUtils.logError(clazz, message, ex);
        return generateErrorResponse(status, message, ex);
    }

    public static <T> ResponseEntity<OkResponse<T>> buildOkResponse(HttpStatus status, T result, String description) {
        return ResponseEntity.status(status).body(new OkResponse<>(true, result, description));
    }

}
