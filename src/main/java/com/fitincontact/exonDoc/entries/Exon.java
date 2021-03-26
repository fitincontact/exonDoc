package com.fitincontact.exonDoc.entries;

import com.fitincontact.exonDoc.enums.ValueType;

public class Exon {
    private final String collection;
    private final ExonEntry exonEntry = new ExonEntry(ValueType.OBJ);

    //todo
    //user
    //execDate

    public Exon(
            final String collection
    ) {
        this.collection = collection;
        this.exonEntry.setMemberName(collection);
    }

    public String getCollection() {
        return collection;
    }

    public ExonEntry getExonEntry() {
        return exonEntry;
    }
}