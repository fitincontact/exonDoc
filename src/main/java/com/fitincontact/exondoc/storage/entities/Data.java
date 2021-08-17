package com.fitincontact.exondoc.storage.entities;

import com.fitincontact.exondoc.storage.interfaces.DataI;

import java.util.HashMap;
import java.util.Map;

public class Data implements DataI {
    public static final Data SINGLETON = new Data();

    private Map<Long, Name> names = new HashMap<>();
    private Map<Long, Value> values = new HashMap<>();

    public void set(
            final Map<Long, Name> names,
            final Map<Long, Value> values
    ) {
        this.names = names;
        this.values = values;
    }

    @Override
    public Data getNewData() {
        return new Data();
    }

    public void insert(final Data data) {
        this.names.putAll(data.names);
        this.values.putAll(data.values);
    }

    public Map<Long, Name> getNames() {
        return names;
    }

    public Map<Long, Value> getValues() {
        return values;
    }

    public void add(final Data data){
        names.putAll(data.names);
        values.putAll(data.values);
    }
}