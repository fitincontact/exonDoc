package com.fitincontact.exondoc.storage;

import com.fitincontact.exondoc.etc.Util;
import com.fitincontact.exondoc.storage.interfaces.InstanceI;
import com.fitincontact.exondoc.storage.interfaces.SequenceI;

import java.nio.charset.Charset;
import java.util.Objects;

public class Sequence implements InstanceI, SequenceI {
    protected static final Sequence SINGLETON = new Sequence();
    private Long id;

    @Override
    public void start() {
        this.id = Long.valueOf(
                Objects.requireNonNull(
                        Util.readFile(
                                "src/main/java/com/fitincontact/exondoc/storage/files/Sequence",
                                Charset.defaultCharset()
                        )
                )
        );
    }

    @Override
    public void stop() {
        save();
    }

    @Override
    public void save() {
        Util.writeFile(
                "src/main/java/com/fitincontact/exondoc/storage/files/Sequence",
                String.valueOf(id)
        );
    }

    @Override
    public Long getId() {
        return id++;
    }

    @Override
    public Long getCurrentId() {
        return id;
    }

    @Override
    public Long getLastSavedId() {
        return Long.valueOf(
                Objects.requireNonNull(
                        Util.readFile(
                                "src/main/java/com/fitincontact/exondoc/storage/files/Sequence",
                                Charset.defaultCharset()
                        )
                )
        );
    }
}