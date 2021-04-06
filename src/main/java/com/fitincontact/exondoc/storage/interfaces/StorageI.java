package com.fitincontact.exondoc.storage.interfaces;

import com.fitincontact.exondoc.storage.entities.Data;

import java.util.Map;

public interface StorageI extends InstanceI, SequenceI, NameI, ValueI/*, StrI, NumI, DateI, PathI, BoolI*/ {
    Map<Long, Primitive> getVals();

    Data getData();
}