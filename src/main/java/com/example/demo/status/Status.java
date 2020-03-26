package com.example.demo.status;

import java.util.stream.Stream;

/**
 * Status enum from table tracking.
 */
public enum Status {

    INITIAL("initial"),

    SHIPPED("shipped"),

    DELIVERED("delivered"),

    CANCELLED("cancelled"),

    RETURNED("returned");

    private final String db;

    /**
     * Status enum.
     * @param db db id
     */
    Status(String db) {
this.db = db;
    }

    public String dbName() {
        return db;
    }

    public static Status of(String db ) {
        return Stream.of(Status.values()).filter( status -> status.dbName().equalsIgnoreCase(db) ).findAny().orElse(Status.INITIAL);
    }
}
