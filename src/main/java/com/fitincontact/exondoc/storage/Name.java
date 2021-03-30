package com.fitincontact.exondoc.storage;

import com.fitincontact.exondoc.storage.enums.Separator;
import com.fitincontact.exondoc.storage.interfaces.InstanceI;
import com.fitincontact.exondoc.storage.interfaces.NameI;
import com.fitincontact.exondoc.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class Name implements InstanceI, NameI {
    protected final static Name SINGLETON = new Name();
    private final List<Name> names = new ArrayList<>();
    private Long nameId;
    private String name;
    private List<String> root;
    private String exonTxt;

    public Name(Long nameId, String name, List<String> root, String exonTxt) {
        this.nameId = nameId;
        this.name = name;
        this.root = root;
        this.exonTxt = exonTxt;
    }

    public Name() {

    }

    public Long getNameId() {
        return nameId;
    }

    public void setNameId(Long nameId) {
        this.nameId = nameId;
    }

    public String getExonTxt() {
        return exonTxt;
    }

    public void setExonTxt(String exonTxt) {
        this.exonTxt = exonTxt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRoot() {
        return root;
    }

    public void setRoot(List<String> root) {
        this.root = root;
    }

    @Override
    public void start() {
        final var rows = Util.getRowsFromFile(
                "src/main/java/com/fitincontact/exondoc/storage/files/Name"
        );
        rows.forEach(row -> {
            final var fields = Util.separator(row, Separator.FIELD);
            add(new Name(
                    Long.valueOf(fields.get(0)),
                    fields.get(1),
                    Util.separator(fields.get(2), Separator.ROOT),
                    fields.get(3)
            ));
        });
        int i = 10;
    }

    @Override
    public void stop() {

    }

    @Override
    public void save() {

    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Name)) {
            return false;
        }

        Name n = (Name) o;

        return n.name.equals(name) &&
                n.nameId.equals(nameId);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = (int) (31 * result + this.nameId);
        return result;
    }

    @Override
    public List<Name> getNames() {
        return names;
    }

    private void add(final Name name) {
        names.add(name);
    }
}