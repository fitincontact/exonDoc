package com.fitincontact.exondoc.storage.interfaces;

import com.fitincontact.exondoc.storage.entities.Value;

import java.util.Map;

public interface ValueI {
    Map<Long, Value> getValues();
}