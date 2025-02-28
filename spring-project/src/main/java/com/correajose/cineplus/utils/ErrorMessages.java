package com.correajose.cineplus.utils;

import jakarta.persistence.EntityNotFoundException;

public final class ErrorMessages {
    public static final String METHOD_NOT_ALLOWED = "Unsupported HTTP method: ";
    public static final String DATA_INTEGRITY_ERROR = "Data integrity error: ";
    public static final String INTERNAL_SERVER_ERROR = "Internal server error: ";
    public static final String RESOURCE_NOT_FOUND = "Resource not found: ";

    public static <T> EntityNotFoundException notFound(String entity, String field, T input){
        return new EntityNotFoundException("Couldn't find "+entity+" with "+field+": "+ input);
    }
}
