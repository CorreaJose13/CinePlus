package com.correajose.cineplus.exceptions.movie;

import com.correajose.cineplus.utils.ErrorResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.correajose.cineplus.utils.ResponseUtils.buildErrorResponse;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class MovieFunctionControllerAdvice {

    @ExceptionHandler(MovieFunctionAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleMovieFunctionAlreadyExistsException(MovieFunctionAlreadyExistsException ex){
        return buildErrorResponse(MovieFunctionControllerAdvice.class, HttpStatus.CONFLICT,ex.getMessage(), ex.getStackTrace());
    }
}
