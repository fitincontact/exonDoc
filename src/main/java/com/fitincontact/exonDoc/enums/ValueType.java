package com.fitincontact.exonDoc.enums;

import java.util.Set;

public enum ValueType {
    OBJ,
    ARR,
    STR,
    NUM,
    DATE,
    PATH,
    BOOL;
    public static final Set<ValueType> primitive = Set.of(STR, NUM, DATE, PATH, BOOL);
}