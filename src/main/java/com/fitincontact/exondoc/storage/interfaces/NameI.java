package com.fitincontact.exondoc.storage.interfaces;

import com.fitincontact.exondoc.storage.entities.Name;

import java.util.List;
import java.util.Map;

public interface NameI {
    Map<Long, Name> getNames();
}