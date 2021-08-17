package com.fitincontact.exondoc.storage.entities;

import com.fitincontact.exondoc.etc.Constant;
import com.fitincontact.exondoc.etc.Util;
import com.fitincontact.exondoc.storage.StorageApi;
import com.fitincontact.exondoc.storage.enums.BoolType;
import com.fitincontact.exondoc.storage.interfaces.InstanceI;
import com.fitincontact.exondoc.storage.interfaces.Primitive;

import java.util.HashMap;
import java.util.Map;

public class Bool implements Primitive<BoolType, Bool>, InstanceI {
    public static final Bool SINGLETON = new Bool();
    private static final String BOOL = Constant.FILES + "Bool";
    final StorageApi api = new StorageApi();
    private final Map<Long, Bool> vals = new HashMap<>();
    private Long id;
    private BoolType val;

    public Bool(final Long id, final BoolType val) {
        this.id = id;
        this.val = val;
    }

    public Bool() {
    }

    @Override
    public void start() {
        final var rows = Util.readPrimitive(BOOL);
        rows.forEach(row -> {
            vals.put(
                    row.getKey(),
                    new Bool(row.getKey(), BoolType.parseElement(row.getValue()))
            );
        });
    }

    @Override
    public void stop() {
        save();
    }

    @Override
    public void save() {
        Util.writePrimitive(vals, BOOL);
    }

    @Override
    public Long getValId() {
        return id;
    }

    @Override
    public BoolType getVal() {
        return val;
    }

    @Override
    public Map<Long, Bool> getVals() {
        return vals;
    }

    @Override
    public void add(final Map<Long, Bool> vals ){
        vals.putAll(vals);
    }
}