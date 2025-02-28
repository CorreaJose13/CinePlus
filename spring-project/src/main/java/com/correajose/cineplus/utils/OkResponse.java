package com.correajose.cineplus.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OkResponse<T> {
    private boolean ok;
    private T result;
    private String description;

    public OkResponse(boolean ok, T result, String description) {
        this.ok = ok;
        this.result = result;
        this.description = description;
    }
}