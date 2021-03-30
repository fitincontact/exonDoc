package com.fitincontact.exondoc.storage;

import com.fitincontact.exondoc.storage.interfaces.StorageI;

import java.util.List;

public class Storage implements StorageI {

    protected final static Storage SINGLETON = new Storage();
    private final Sequence sequence = Sequence.SINGLETON;
    private final Name name = Name.SINGLETON;

    @Override
    public void start() {
        sequence.start();
        name.start();
    }

    @Override
    public void stop() {
        sequence.stop();
        name.stop();
    }

    @Override
    public void save() {
        sequence.save();
        name.save();
    }

    @Override
    public Long getId() {
        return sequence.getId();
    }

    @Override
    public Long getCurrentId() {
        return sequence.getCurrentId();
    }

    @Override
    public Long getLastSavedId() {
        return sequence.getLastSavedId();
    }

    @Override
    public List<Name> getNames() {
        return name.getNames();
    }
}