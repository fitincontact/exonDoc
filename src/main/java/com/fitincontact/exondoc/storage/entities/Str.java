package com.fitincontact.exondoc.storage.entities;

import com.fitincontact.exondoc.etc.Constant;
import com.fitincontact.exondoc.etc.Util;
import com.fitincontact.exondoc.storage.StorageApi;
import com.fitincontact.exondoc.storage.interfaces.InstanceI;
import com.fitincontact.exondoc.storage.interfaces.Primitive;

import java.util.HashMap;
import java.util.Map;

public class Str implements Primitive<String, Str>, InstanceI {
    public static final Str SINGLETON = new Str();
    private static final String STR = Constant.FILES + "Str";
    final StorageApi api = new StorageApi();
    private final Map<Long, Str> vals = new HashMap<>();
    private Long id;
    private String val;

    public Str(final Long id, final String val) {
        this.id = id;
        this.val = val;
    }

    public Str() {
    }
//
//    @Override
//    public Map<Long, Str> getStrs() {
//        return strs;
//    }

    @Override
    public void start() {
        final var rows = Util.readPrimitive(STR);
//        rows.forEach(row -> {
//            strs.put(
//                    row.id(),
//                    new Str(row.id(), row.val())
//            );
//        });
        rows.forEach(row -> {
            vals.put(
                    row.getKey(),
                    new Str(row.getKey(), row.getValue())
            );
        });
    }

    @Override
    public void stop() {
        save();
    }

    @Override
    public void save() {
        Util.writePrimitive(vals, STR);

    }

    @Override
    public Long getValId() {
        return id;
    }

//    @Override
//    public String getStringVal() {
//        return val;
//    }

    @Override
    public Map<Long, Str> getVals() {
        return vals;
    }

    @Override
    public String getVal() {
        return val;
    }
}