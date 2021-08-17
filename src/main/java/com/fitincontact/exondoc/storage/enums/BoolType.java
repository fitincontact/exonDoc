package com.fitincontact.exondoc.storage.enums;

public enum BoolType {
    TRUE("t","true"),
    FALSE("f","false"),
    NIL("n","nil");
    private final String element;
    private final String literal;

    BoolType(final String element, final String literal) {
        this.element = element;
        this.literal = literal;
    }

    public static BoolType parseElement(final String element) {
        for (final var type : BoolType.values()) {
            if (type.name().equals(element)) {
                return type;
            }
        }
        return null;
    }

    public static BoolType parseLiteral(final String literal) {
        for (final var type : BoolType.values()) {
            if (type.getLiteral().equals(literal)) {
                return type;
            }
        }
        return null;
    }

    public String getLiteral() {
        return literal;
    }
}