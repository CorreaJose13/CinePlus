package com.correajose.cineplus.exceptions.customer;

import jakarta.validation.constraints.Email;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(@Email String s) {
        super("Customer with email '" + s + "' already exists");
    }
}
