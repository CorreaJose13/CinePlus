package com.correajose.cineplus.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class ResponseUtils {

    public static ResponseEntity<ErrorResponse> buildErrorResponse(Class<?> clazz,HttpStatus status, String message, StackTraceElement[] ex) {
        LoggingUtils.logError(clazz, message, ex);
        ErrorResponse errorResponse = new ErrorResponse(
                false,
                status.value(),
                message
        );
        return ResponseEntity.status(status).body(errorResponse);
    }

    public static <T> ResponseEntity<OkResponse<T>> buildOkResponse(HttpStatus status, T result, String description) {
        return ResponseEntity.status(status).body(new OkResponse<>(true, result, description));
    }

}
