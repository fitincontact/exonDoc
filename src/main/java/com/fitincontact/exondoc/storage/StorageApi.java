package com.fitincontact.exondoc.storage;

import com.fitincontact.exondoc.parser.entries.Exon;
import com.fitincontact.exondoc.storage.entities.Data;
import com.fitincontact.exondoc.storage.entities.Name;
import com.fitincontact.exondoc.storage.entities.Value;
import com.fitincontact.exondoc.storage.enums.StorageState;
import com.fitincontact.exondoc.storage.interfaces.Primitive;
import com.fitincontact.exondoc.storage.interfaces.StorageI;

import java.util.Map;

public class StorageApi implements StorageI {
    private static final StorageI storage = Storage.SINGLETON;
    private static final StorageI NULL = StorageNull.SINGLETON;
    private static final ExonToStorage exonToStorage = new ExonToStorage();
    private static StorageI work;
    private static StorageState state = StorageState.SINGLETON;

    @Override
    public void start() {
        work = storage;
        storage.start();
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

    public Storage getStorage() {
        return Storage.SINGLETON;
    }

    public void insert(final Exon exon) {
        final var storage = exonToStorage.convert(exon);

    }

    @Override
    public Map<Long, Primitive> getVals() {
        return work.getVals();
    }

    @Override
    public Data getData() {
        return work.getData();
    }

    @Override
    public Map<Long, Name> getNames() {
        return work.getNames();
    }

    @Override
    public Map<Long, Value> getValues() {
        return work.getValues();
    }
}