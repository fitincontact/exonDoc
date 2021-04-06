package com.fitincontact.exondoc.storage;

import com.fitincontact.exondoc.parser.entries.Exon;

public class ExonToStorage {
    StorageApi api = new StorageApi();

    public Storage convert(final Exon exon) {
        final var storage = api.getStorage();


        return storage;
    }
}