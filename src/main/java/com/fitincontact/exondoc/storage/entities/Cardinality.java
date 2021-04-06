package com.fitincontact.exondoc.storage.entities;

import com.fitincontact.exondoc.etc.Constant;
import com.fitincontact.exondoc.etc.Util;
import com.fitincontact.exondoc.storage.StorageApi;
import com.fitincontact.exondoc.storage.enums.Separator;
import com.fitincontact.exondoc.storage.interfaces.CardinalityI;
import com.fitincontact.exondoc.storage.interfaces.InstanceI;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class Cardinality implements CardinalityI, InstanceI {
    public static final Cardinality SINGLETON = new Cardinality();
    private static final String CARDINALITY = Constant.FILES + "Cardinality";
    final StorageApi api = new StorageApi();
    private final Map<String, Long> cardinalities = new HashMap<>();

    private String name;
    private Long cardinality;

    public Cardinality(final String name, final Long cardinality) {
        this.name = name;
        this.cardinality = cardinality;
    }

    public Cardinality() {
    }

    @Nullable
    @Override
    public Long getCardinality(final String name) {
        if (cardinalities.containsKey(name)) {
            return cardinalities.get(name);
        }
        return null;
    }

    @Override
    public void set(final String name) {
        if (cardinalities.containsKey(name)) {
            cardinalities.put(name, cardinalities.get(name) + 1);
        } else {
            cardinalities.put(name, 0L);
        }
    }

    @Override
    public void start() {
        final var rows = Util.getRowsFromFile(CARDINALITY);
        rows.forEach(row -> {
            final var fields = Util.separator(row, Separator.FIELD);
            cardinalities.put(
                    fields.get(0),
                    Long.valueOf(fields.get(1)
                    )
            );
        });
    }

    @Override
    public void stop() {
        save();
    }

    @Override
    public void save() {
        final var file = new StringBuilder();

        for (final var entry : cardinalities.entrySet()) {
            file
                    .append(entry.getKey())
                    .append(Separator.FIELD.getSeparator())
                    .append(entry.getValue())
                    .append(Separator.FIELD.getSeparator())
                    .append("\n");
        }
        //todo if size = 0
        Util.writeFile(
                CARDINALITY,
                file.substring(0, file.length() - 1)
        );
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Cardinality)) {
            return false;
        }

        final Cardinality n = (Cardinality) o;

        return n.name.equals(name) &&
               n.cardinality.equals(cardinality);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.name.hashCode();
        return result;
    }
}