package com.fitincontact.exondoc.storage.entities;

import com.fitincontact.exondoc.etc.Constant;
import com.fitincontact.exondoc.etc.Util;
import com.fitincontact.exondoc.storage.StorageApi;
import com.fitincontact.exondoc.storage.interfaces.InstanceI;
import com.fitincontact.exondoc.storage.interfaces.Primitive;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Date implements Primitive<LocalDateTime, Date>, InstanceI {
    public static final Date SINGLETON = new Date();
    private static final String DATE = Constant.FILES + "Date";
    final StorageApi api = new StorageApi();
    private final Map<Long, Date> vals = new HashMap<>();
    private Long id;
    private LocalDateTime val;

    public Date(final Long id, final LocalDateTime val) {
        this.id = id;
        this.val = val;
    }

    public Date() {
    }

    @Override
    public void start() {
        final var rows = Util.readPrimitive(DATE);
        rows.forEach(row -> {
            vals.put(
                    row.getKey(),
                    //DateTimeFormatter.ISO_LOCAL_DATE_TIME
                    //2007-12-03T10:15:30
                    new Date(row.getKey(), LocalDateTime.parse(row.getValue()))
            );
        });
    }

    @Override
    public void stop() {
        save();
    }

    @Override
    public void save() {
        Util.writePrimitive(vals, DATE);
    }

    @Override
    public Long getValId() {
        return id;
    }

    @Override
    public LocalDateTime getVal() {
        return val;
    }

    @Override
    public Map<Long, Date> getVals() {
        return vals;
    }
}