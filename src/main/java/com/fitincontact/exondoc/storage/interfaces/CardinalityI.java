package com.fitincontact.exondoc.storage.interfaces;

public interface CardinalityI {
    Long getCardinality(final String name);

    void set(final String name);
}