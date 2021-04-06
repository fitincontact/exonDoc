package com.fitincontact.exondoc.storage.entities;

import com.fitincontact.exondoc.etc.Constant;
import com.fitincontact.exondoc.etc.Util;
import com.fitincontact.exondoc.storage.StorageApi;
import com.fitincontact.exondoc.storage.enums.Separator;
import com.fitincontact.exondoc.storage.enums.ValueFileType;
import com.fitincontact.exondoc.storage.interfaces.InstanceI;
import com.fitincontact.exondoc.storage.interfaces.Primitive;
import com.fitincontact.exondoc.storage.interfaces.ValueI;

import java.util.HashMap;
import java.util.Map;

public class Value implements ValueI, InstanceI {
    public static final Value SINGLETON = new Value();
    private static final String VALUE = Constant.FILES + "Value";
    private final StorageApi api = new StorageApi();
    private final Map<Long, Value> values = new HashMap<>();

    private Long valueId;
    private Name name;
    private Reference reference;

    public Value(
            final Long valueId,
            final Name name,
            final Reference reference
    ) {
        this.valueId = valueId;
        this.name = name;
        this.reference = reference;
    }

    public Value() {
    }

    public static Value getSINGLETON() {
        return SINGLETON;
    }

    @Override
    public Map<Long, Value> getValues() {
        return values;
    }

    public Long getValueId() {
        return valueId;
    }

    public Name getName() {
        return name;
    }

    public Reference getReference() {
        return reference;
    }

    @Override
    public void start() {
        final var rows = Util.getRowsFromFile(VALUE);
        rows.forEach(row -> {
            final var fields = Util.separator(row, Separator.FIELD);
            final var names = api.getNames();

            final var name = names.get(Long.valueOf(fields.get(1)));
            final var valueFileType = ValueFileType.getValueFileTypeByElement(fields.get(2));

            final var vals = api.getVals();

            Name nameReference = null;
            Primitive primitiveReference = null;
            switch (valueFileType) {
                case OBJ, ARR, MEM -> {
                    nameReference = names.get(Long.valueOf(fields.get(3)));
                }
                case STR, NUM, DATE, PATH, BOOL -> {
                    primitiveReference = vals.get(Long.valueOf(fields.get(0)));
                }
            }

            final Reference reference = new Reference(
                    valueFileType,
                    nameReference,
                    primitiveReference
            );

            add(new Value(
                    Long.valueOf(fields.get(0)),
                    name,
                    reference
            ));
        });
    }

    @Override
    public void stop() {
        save();
    }

    @Override
    public void save() {

    }

    private void add(final Value value) {
        values.put(value.valueId, value);
    }
}