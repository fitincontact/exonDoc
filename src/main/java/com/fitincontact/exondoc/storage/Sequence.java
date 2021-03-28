package com.fitincontact.exondoc.storage;

import com.fitincontact.exondoc.utils.Util;

import java.nio.charset.Charset;
import java.util.Objects;

public class Sequence implements Instance {
    protected static Sequence SINGLETON = new Sequence(false);
    private Long id;
    private boolean isTemp = true;

    private Sequence(boolean i) {
    }

    private Sequence() {
        this.id = Long.valueOf(
                Objects.requireNonNull(
                        Util.readFile(
                                "src/main/java/com/fitincontact/exondoc/storage/files/Sequence",
                                Charset.defaultCharset()
                        )
                )
        );
        isTemp = false;
    }

    public static Long getLastSavedId() {
        return Long.valueOf(
                Objects.requireNonNull(
                        Util.readFile(
                                "src/main/java/com/fitincontact/exondoc/storage/files/Sequence",
                                Charset.defaultCharset()
                        )
                )
        );
    }

    @Override
    public void start() {
        if (isTemp) {
            SINGLETON = new Sequence();
        }
    }

    @Override
    public void stop() {
        Util.writeFile(
                "src/main/java/com/fitincontact/exondoc/storage/files/Sequence",
                String.valueOf(id)
        );
        //todo ?
        SINGLETON = null;
    }

    @Override
    public void save() {
        Util.writeFile(
                "src/main/java/com/fitincontact/exondoc/storage/files/Sequence",
                String.valueOf(id)
        );
    }

    protected Sequence getInstance() {
        return SINGLETON;
    }

    public Long getId() {
        return id++;
    }

    public Long getCurrentId() {
        return id;
    }
}