package com.fitincontact.exondoc.storage.enums;

import com.fitincontact.exondoc.storage.Str;

public enum Separator {
    FIELD("|"),
    END_CORTEGE("\n"),
    //END_FILE("!"),
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