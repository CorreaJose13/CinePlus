package com.correajose.cineplus.models.purchase;

public enum Status {
    SUCCESSFUL("successful"),
    FAILED("failed"),
    PENDING("pending");

    public final String label;

    private Status(String label) {
        this.label = label;
    }
}
