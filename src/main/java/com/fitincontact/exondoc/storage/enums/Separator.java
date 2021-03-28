package com.fitincontact.exondoc.storage.enums;

public enum Separator {
    LEFT("<|"),
    RIGHT("|>"),
    END_CORTEGE(">>"),
    ROOT(".")
    ;
    private final String separator;

    Separator(final String separator) {
        this.separator = separator;
    }

    public String getSeparator() {
        return separator;
    }
}