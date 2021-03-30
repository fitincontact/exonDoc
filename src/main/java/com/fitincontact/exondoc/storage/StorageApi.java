package com.fitincontact.exondoc.storage;

import com.fitincontact.exondoc.storage.enums.StorageState;
import com.fitincontact.exondoc.storage.interfaces.StorageI;

import java.util.List;

public class StorageApi implements StorageI {
    private final StorageI storage = Storage.SINGLETON;
    private final StorageI NULL = StorageNull.SINGLETON;
    private StorageI work;
    private StorageState state = StorageState.SINGLETON;

    @Override
    public void start() {
        storage.start();
        work = storage;
        state = StorageState.START;
    }

    @Override
    public void stop() {
        storage.stop();
        work = NULL;
        state = StorageState.STOP;
    }

    @Override
    public void save() {
        work.save();
    }

    @Override
    public Long getId() {
        return work.getId();
    }

    @Override
    public Long getCurrentId() {
        return work.getCurrentId();
    }

    @Override
    public Long getLastSavedId() {
        return work.getLastSavedId();
    }

    @Override
    public List<Name> getNames() {
        return work.getNames();
    }
}