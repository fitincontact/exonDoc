package com.fitincontact.exondoc.storage.interfaces;

import java.util.Map;

public interface Primitive<T, Y> {
    Long getValId();

    <T> T getVal();

//    @Deprecated
//    String getStringVal();

    Map<Long, Y> getVals();
}