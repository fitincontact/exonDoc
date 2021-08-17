package com.fitincontact.exondoc.storage.entities;

import com.fitincontact.exondoc.etc.Constant;
import com.fitincontact.exondoc.etc.Util;
import com.fitincontact.exondoc.storage.StorageApi;
import com.fitincontact.exondoc.storage.interfaces.InstanceI;
import com.fitincontact.exondoc.storage.interfaces.Primitive;

import java.util.HashMap;
import java.util.Map;

public class Path implements Primitive<String, Path>, InstanceI {
    public static final Path SINGLETON = new Path();
    private static final String PATH = Constant.FILES + "Path";
    final StorageApi api = new StorageApi();
    private final Map<Long, Path> vals = new HashMap<>();
    private Long id;
    private String val;

    public Path(final Long id, final String val) {
        this.id = id;
        this.val = val;
    }

    public Path() {
    }

    @Override
    public void start() {
        final var rows = Util.readPrimitive(PATH);
        rows.forEach(row -> {
            vals.put(
                    row.getKey(),
                    new Path(row.getKey(), row.getValue())
            );
        });
    }

    @Override
    public void stop() {
        save();
    }

    @Override
    public void save() {
        Util.writePrimitive(vals, PATH);
    }

    @Override
    public Long getValId() {
        return id;
    }

    @Override
    public String getVal() {
        return val;
    }

    @Override
    public Map<Long, Path> getVals() {
        return vals;
    }

    @Override
    public void add(final Map<Long, Path> vals ){
        vals.putAll(vals);
    }
}