package com.fitincontact.exondoc.storage;

import com.fitincontact.exondoc.parser.entries.Exon;
import com.fitincontact.exondoc.storage.entities.Data;
import com.fitincontact.exondoc.storage.entities.Name;
import com.fitincontact.exondoc.storage.entities.Value;
import com.fitincontact.exondoc.storage.enums.StorageState;
import com.fitincontact.exondoc.storage.interfaces.Command;
import com.fitincontact.exondoc.storage.interfaces.Primitive;
import com.fitincontact.exondoc.storage.interfaces.StorageI;
import org.apache.log4j.Logger;

import java.util.Map;

public class StorageApi implements StorageI<Exon> {
    static final Logger LOG = Logger.getLogger(StorageApi.class);
    private static final StorageI storage = Storage.SINGLETON;
    private static final StorageI NULL = StorageNull.SINGLETON;
    private static final ExonToStorage exonToStorage = new ExonToStorage();
    private static StorageI work;
    private static StorageState state = StorageState.SINGLETON;

    @Override
    public void start() {
        LOG.debug("api start - start");
        work = storage;
        storage.start();
        state = StorageState.START;
        LOG.debug("api start - stop");
    }

    @Override
    public void stop() {
        LOG.debug("api stop - start");
        storage.stop();
        work = NULL;
        state = StorageState.STOP;
        LOG.debug("api stop - stop");
    }

    @Override
    public void save() {
        LOG.debug("api save - start");
        work.save();
        LOG.debug("api save - stop");
    }

    @Override
    public Long getId() {

        LOG.debug("api getId");
        return work.getId();
    }

    @Override
    public Long getCurrentId() {
        LOG.debug("api getCurrentId");
        return work.getCurrentId();
    }

    @Override
    public Long getLastSavedId() {
        LOG.debug("api getLastSavedId");
        return work.getLastSavedId();
    }

    @Override
    public Data getNewData() {
        LOG.debug("api getNewData");
        return work.getNewData();
    }

    @Override
    public void insert(final Exon exon) {
        LOG.debug("api insert - start");
        final var data = exonToStorage.convert(exon);
        work.insert(data);
        LOG.debug("api insert - stop");
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