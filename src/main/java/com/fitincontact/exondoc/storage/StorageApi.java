package com.fitincontact.exondoc.storage;

public class StorageApi implements Instance {
    private final Sequence sequenceTmp = Sequence.SINGLETON;
    private Sequence sequence = null;

    public Sequence sequence() {
        return sequence;
    }

    @Override
    public void start() {
        sequenceTmp.start();
        sequence = sequenceTmp.getInstance();
    }

    @Override
    public void stop() {

    }
}