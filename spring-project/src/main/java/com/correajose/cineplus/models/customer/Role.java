package com.correajose.cineplus.models.customer;

public enum Role {
    admin("admin"),
    user("user");

    public final String label;

    private Role(String label){
        this.label= label;
    }
}
