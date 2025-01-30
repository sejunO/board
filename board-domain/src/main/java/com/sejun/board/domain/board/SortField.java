package com.sejun.board.domain.board;

import java.util.Arrays;

public enum SortField {
    ID("id"),
    TITLE("title"),
    CREATED_AT("createdAt");

    private final String fieldName;

    SortField(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    // default value: ID
    public static SortField from(String value) {
        return Arrays.stream(SortField.values())
                .filter(type -> type.name().equalsIgnoreCase(value))
                .findFirst()
                .orElse(ID);
    }
}
