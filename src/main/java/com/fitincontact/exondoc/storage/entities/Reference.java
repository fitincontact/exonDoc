package com.fitincontact.exondoc.storage.entities;

import com.fitincontact.exondoc.storage.enums.ValueFileType;
import com.fitincontact.exondoc.storage.interfaces.Primitive;

public class Reference {
    private final ValueFileType valueFileType;
    private final Name name;
    private final Primitive primitive;

    public Reference(
            final ValueFileType valueFileType,
            final Name name,
            final Primitive primitive
    ) {
        this.valueFileType = valueFileType;
        this.name = name;
        this.primitive = primitive;
    }

    public ValueFileType getValueFileType() {
        return valueFileType;
    }

    public Name getName() {
        return name;
    }

    public Primitive getPrimitive() {
        return primitive;
    }
}