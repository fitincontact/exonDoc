package com.fitincontact.exondoc.storage.enums;

import java.util.Arrays;

public enum ValueFileType {
    OBJ("o"),
    ARR("a"),
    MEM("m"),
    STR("s"),
    NUM("n"),
    DATE("d"),
    PATH("p"),
    BOOL("b");
    private final String element;

    ValueFileType(final String element) {
        this.element = element;
    }

    public static ValueFileType getValueFileTypeByElement(final String element) {
        return Arrays
                .stream(ValueFileType.values())
                .filter(valueFileType -> valueFileType.element.equals(element))
                .findFirst()
                .orElse(null);
    }
}