package com.fitincontact.exondoc.storage.entities;

import com.fitincontact.exondoc.etc.Constant;
import com.fitincontact.exondoc.etc.Util;
import com.fitincontact.exondoc.storage.StorageApi;
import com.fitincontact.exondoc.storage.interfaces.InstanceI;
import com.fitincontact.exondoc.storage.interfaces.Primitive;

import java.util.HashMap;
import java.util.Map;

public class Num implements Primitive<Double, Num>, InstanceI {
    public static final Num SINGLETON = new Num();
    private static final String NUM = Constant.FILES + "Num";
    final StorageApi api = new StorageApi();
    private final Map<Long, Num> vals = new HashMap<>();
    private Long id;
    private Double val;

    public Num(
            final Long id,
            final Double val
    ) {
        this.id = id;
        this.val = val;
    }

    public Num() {
    }

    @Override
    public void start() {
        final var rows = Util.readPrimitive(NUM);
        rows.forEach(row -> {
            vals.put(
                    row.getKey(),
                    new Num(row.getKey(), Double.valueOf(row.getValue()))
            );
        });
    }

    @Override
    public void stop() {
        save();
    }

    @Override
    public void save() {
        Util.writePrimitive(vals, NUM);
    }

    @Override
    public Long getValId() {
        return id;
    }

    @Override
    public Double getVal() {
        return val;
    }

    @Override
    public Map<Long, Num> getVals() {
        return vals;
    }
}