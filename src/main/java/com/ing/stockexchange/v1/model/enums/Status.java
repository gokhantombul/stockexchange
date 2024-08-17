package com.ing.stockexchange.v1.model.enums;

public enum Status {
    ACTIVE("Active"),
    PASSIVE("Passive"),
    DELETED("Deleted");

    private String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
