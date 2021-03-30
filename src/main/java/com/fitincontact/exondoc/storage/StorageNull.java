package com.fitincontact.exondoc.storage;

import com.fitincontact.exondoc.storage.interfaces.StorageI;

import java.util.List;

public class StorageNull implements StorageI {
    protected final static StorageNull SINGLETON = new StorageNull();

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
    public List<Name> getNames() {
        return null;
    }
}
