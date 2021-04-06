package com.fitincontact.exondoc.storage.entities;

import java.util.Map;

public class Data {
    public static final Data SINGLETON = new Data();


    private  Map<Long, Name> names;
    private  Map<Long, Value> values;

    public void set(
            final Map<Long, Name> names,
            final Map<Long, Value> values
    ) {
        this.names = names;
        this.values = values;
    }
}