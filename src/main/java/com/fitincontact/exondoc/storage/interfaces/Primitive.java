package com.fitincontact.exondoc.storage.interfaces;

import java.util.Map;

public interface Primitive<T, Y> {
    Long getValId();

    <T> T getVal();

    Map<Long, Y> getVals();

    void add(final Map<Long, Y> vals );
}