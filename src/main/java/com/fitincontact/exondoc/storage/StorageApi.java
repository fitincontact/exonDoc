package com.fitincontact.exondoc.storage;

public class StorageApi implements Instance {
    private final Storage storage = Storage.SINGLETON;
    //private Storage storage = null;

    @Override
    public void start() {
        storage.start();
    }

    @Override
    public void stop() {
        storage.stop();
    }

    @Override
    public void save() {
        storage.save();
    }

    public Long getId() {
        return storage.getSequence().getId();
    }

    public Long getCurrentId() {
        return storage.getSequence().getCurrentId();
    }

    public Long getLastSavedId() {
        return Sequence.getLastSavedId();
    }
}