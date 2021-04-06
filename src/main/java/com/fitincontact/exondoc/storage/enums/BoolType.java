package com.fitincontact.exondoc.storage.enums;

public enum BoolType {
    TRUE("t"),
    FALSE("f"),
    NIL("n");
    private final String element;

    BoolType(final String element) {
        this.element = element;
    }

    public static BoolType parse(final String element) {
        for (final var type : BoolType.values()) {
            if (type.name().equals(element)) {
                return type;
            }
        }
        return null;
    }
}