package com.fitincontact.exondoc.storage.entities;

import com.fitincontact.exondoc.etc.Constant;
import com.fitincontact.exondoc.etc.Util;
import com.fitincontact.exondoc.storage.enums.Separator;
import com.fitincontact.exondoc.storage.interfaces.InstanceI;
import com.fitincontact.exondoc.storage.interfaces.NameI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Name implements InstanceI, NameI {

    public static final Name SINGLETON = new Name();
    private static final String NAME = Constant.FILES + "Name";

    private final Map<Long, Name> names = new HashMap<>();
    private Long nameId;
    private String name;
    private List<String> root;
    private String exonTxt;

    public Name(
            final Long nameId,
            final String name,
            final List<String> root,
            final String exonTxt
    ) {
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

    public void setNameId(final Long nameId) {
        this.nameId = nameId;
    }

    public String getExonTxt() {
        return exonTxt;
    }

    public void setExonTxt(final String exonTxt) {
        this.exonTxt = exonTxt;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<String> getRoot() {
        return root;
    }

    public void setRoot(final List<String> root) {
        this.root = root;
    }

    @Override
    public void start() {
        final var rows = Util.getRowsFromFile(NAME);
        rows.forEach(row -> {
            final var fields = Util.separator(row, Separator.FIELD);
            if (!row.equals("")) {
                add(new Name(
                        Long.valueOf(fields.get(0)),
                        fields.get(1),
                        Util.separator(fields.get(2), Separator.ROOT),
                        fields.get(3)
                ));
            }
        });
    }

    @Override
    public void stop() {
        save();
    }

    @Override
    public void save() {
        final var file = new StringBuilder();

        for (final var entry : names.entrySet()) {
            file
                    .append(entry.getValue().nameId)
                    .append(Separator.FIELD.getSeparator())
                    .append(entry.getValue().name)
                    .append(Separator.FIELD.getSeparator());

            final var roots = new StringBuilder();
            if (entry.getValue().root != null) {
                for (final String root : entry.getValue().root) {
                    roots.append(root).append(Separator.ROOT.getSeparator());
                }
            }
            //todo if size = 0
            if (!roots.toString().equals("")) {
                file
                        .append(roots.substring(0, roots.length() - 1))
                        .append(Separator.FIELD.getSeparator())
                        .append(entry.getValue().exonTxt)
                        .append(Separator.FIELD.getSeparator())
                        .append("\n");
            }
        }
        if (!file.toString().equals("")) {
            Util.writeFile(
                    NAME,
                    file.substring(0, file.length() - 1)
            );
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Name)) {
            return false;
        }

        final Name n = (Name) o;

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
    public Map<Long, Name> getNames() {
        return names;
    }

    private void add(final Name name) {
        names.put(name.nameId, name);
    }
}