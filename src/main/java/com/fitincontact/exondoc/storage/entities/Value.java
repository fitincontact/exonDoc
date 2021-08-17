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

import static com.fitincontact.exondoc.storage.enums.ValueFileType.NONPRIMITIVE;

public class Value implements ValueI, InstanceI {
    public static final Value SINGLETON = new Value();
    private static final String VALUE = Constant.FILES + "Value";
    private final StorageApi api = new StorageApi();

    private final Str str = Str.SINGLETON;
    private final Num num = Num.SINGLETON;
    private final Date date = Date.SINGLETON;
    private final Path path = Path.SINGLETON;
    private final Bool bool = Bool.SINGLETON;

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
            if (!row.equals("")) {
                final var fields = Util.separator(row, Separator.FIELD);
                final var names = api.getNames();

                final var name = names.get(Long.valueOf(fields.get(1)));
                final var valueFileType = ValueFileType.getValueFileTypeByElement(fields.get(2));

                final var vals = api.getVals();

                Name nameReference = null;
                Primitive primitiveReference = null;

                if (NONPRIMITIVE.contains(valueFileType)) {
                    nameReference = names.get(Long.valueOf(fields.get(3)));
                } else {
                    primitiveReference = vals.get(Long.valueOf(fields.get(0)));
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

        for (final var entry : values.entrySet()) {
            final var referenceFile = new StringBuilder();
            referenceFile.append(
                    NONPRIMITIVE.contains(entry.getValue().getReference().getValueFileType())
                            ? ""
                            : entry.getValue().getReference().getPrimitive().getValId()
            )
                    .append(Separator.FIELD.getSeparator());

            file
                    .append(entry.getValue().valueId)
                    .append(Separator.FIELD.getSeparator())
                    .append(entry.getValue().name.getNameId())
                    .append(Separator.FIELD.getSeparator())
                    .append(entry.getValue().getReference().getValueFileType().getElement())
                    .append(Separator.FIELD.getSeparator())
                    .append(referenceFile)
                    .append("\n")
            ;
        }
        if (!file.toString().equals("")) {
            Util.writeFile(
                    VALUE,
                    file.substring(0, file.length() - 1)
            );
        }

    }

    private void add(final Value value) {
        values.put(value.valueId, value);
    }
}