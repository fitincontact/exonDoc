package com.fitincontact.exondoc.storage.interfaces;

import com.fitincontact.exondoc.storage.entities.Data;

import java.util.Map;

public interface StorageI<T> extends InstanceI, SequenceI, NameI, ValueI,DataI,Command<T>/*, StrI, NumI, DateI, PathI, BoolI*/ {
    Map<Long, Primitive> getVals();

    Data getData();
}