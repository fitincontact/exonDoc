package com.fitincontact.exondoc.storage;

public class Storage implements Instance {

    protected final static Storage SINGLETON = new Storage();
    private final Sequence sequenceTmp = Sequence.SINGLETON;
    private Sequence sequence;

    @Override
    public void start() {
        sequenceTmp.start();
        sequence = sequenceTmp.getInstance();
    }

    @Override
    public void stop() {
        sequence.stop();
        sequence = null;
    }

    @Override
    public void save() {
        sequence.save();
    }

    protected Storage getInstance() {
        return SINGLETON;
    }

    protected Sequence getSequence() {
        return sequence;
    }


}