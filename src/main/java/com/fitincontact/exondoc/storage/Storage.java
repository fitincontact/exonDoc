package com.fitincontact.exondoc.storage;

import com.fitincontact.exondoc.storage.entities.*;
import com.fitincontact.exondoc.storage.interfaces.Command;
import com.fitincontact.exondoc.storage.interfaces.Primitive;
import com.fitincontact.exondoc.storage.interfaces.StorageI;

import java.util.HashMap;
import java.util.Map;

public class Storage implements StorageI<Data> {

    protected static final Storage SINGLETON = new Storage();
    private static final Map<Long, Primitive> vals = new HashMap<>();
    private final Sequence sequence = Sequence.SINGLETON;
    private final Cardinality cardinality = Cardinality.SINGLETON;
    private final Name name = Name.SINGLETON;
    private final Value value = Value.SINGLETON;
    private final Str str = Str.SINGLETON;
    private final Num num = Num.SINGLETON;
    private final Date date = Date.SINGLETON;
    private final Path path = Path.SINGLETON;
    private final Bool bool = Bool.SINGLETON;
    private final Data data = Data.SINGLETON;

    @Override
    public void start() {
        sequence.start();
        cardinality.start();
        name.start();

        str.start();
        num.start();
        date.start();
        path.start();
        bool.start();

        vals.putAll(str.getVals());
        vals.putAll(num.getVals());
        vals.putAll(date.getVals());
        vals.putAll(path.getVals());
        vals.putAll(bool.getVals());

        value.start();
        data.set(name.getNames(), value.getValues());
    }

    @Override
    public void stop() {
        sequence.stop();
        cardinality.stop();
        name.stop();
        value.stop();
        str.stop();
        num.stop();
        date.stop();
        path.stop();
        bool.stop();
    }

    @Override
    public void save() {
        sequence.save();
        cardinality.save();
        name.save();
        value.save();
        str.save();
        num.save();
        date.save();
        path.save();
        bool.save();
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
    public Data getData() {
        return data;
    }

    @Override
    public Map<Long, Name> getNames() {
        return name.getNames();
    }

    @Override
    public Map<Long, Value> getValues() {
        return value.getValues();
    }

    @Override
    public Map<Long, Primitive> getVals() {
        return vals;
    }

    @Override
    public Data getNewData() {
        return data.getNewData();
    }

    @Override
    public void insert(final Data dataNew) {
        data.insert(dataNew);
        save();
    }
}