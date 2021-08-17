package com.fitincontact.exondoc.storage;

import com.fitincontact.exondoc.storage.entities.Data;
import com.fitincontact.exondoc.storage.entities.Name;
import com.fitincontact.exondoc.storage.entities.Value;
import com.fitincontact.exondoc.storage.interfaces.Command;
import com.fitincontact.exondoc.storage.interfaces.Primitive;
import com.fitincontact.exondoc.storage.interfaces.StorageI;

import java.util.Map;

public class StorageNull implements StorageI<Data> {
    protected static final StorageNull SINGLETON = new StorageNull();

    @Override
    public void start() {
    }

    @Override
    public void stop() {
    }

    @Override
    public void save() {
    }

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public Long getCurrentId() {
        return null;
    }

    @Override
    public Long getLastSavedId() {
        return null;
    }

    @Override
    public Map<Long, Primitive> getVals() {
        return null;
    }

    @Override
    public Data getData() {
        return null;
    }

    @Override
    public Map<Long, Name> getNames() {
        return null;
    }

    @Override
    public Map<Long, Value> getValues() {
        return null;
    }

    @Override
    public Data getNewData() {
        return null;
    }

    @Override
    public void insert(Data data) {

    }
}