package com.fitincontact.exondoc.storage;

import com.fitincontact.exondoc.parser.entries.Exon;
import com.fitincontact.exondoc.storage.entities.*;
import com.fitincontact.exondoc.storage.enums.BoolType;
import com.fitincontact.exondoc.storage.enums.ValueFileType;
import com.fitincontact.exondoc.storage.interfaces.Primitive;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ExonToStorage {
    static final Logger LOG = Logger.getLogger(ExonToStorage.class);
    StorageApi api = new StorageApi();

    public Data convert(final Exon exon){
        return exon.getExonEntry().convert(new StringBuilder(exon.getCollection()),true);
    }

    public Data convert2(final Exon exon) {
        final var data = api.getNewData();

        final var names = data.getNames();
        final var values = data.getValues();

        final var collectName = new Name(
                api.getId(),
                exon.getCollection(),
                new ArrayList<>(),
                exon.getExonEntry().toStringInLine()
        );
        names.put(collectName.getNameId(), collectName);

        final var root = new StringBuilder(collectName.getName());
        for (final var entry : exon.getExonEntry().getArrAndObjList()) {
            final var valueType = entry.getValueType();
            switch (valueType) {
                case OBJ -> {
                }
                case ARR -> {
                }
                case STR -> {
                    final var name = new Name(
                            api.getId(),
                            entry.getMemberName(),
                            List.of(root.toString()),
                            ""
                    );
                    final var str = new Str(
                            name.getNameId(),
                            entry.getStr()
                    );
                    names.put(name.getNameId(), name);
                    final var reference = new Reference(
                            ValueFileType.STR,
                            null,
                            str
                    );
                    final var value = new Value(
                            api.getId(),
                            name,
                            reference
                    );
                    values.put(name.getNameId(), value);
                }
                case NUM -> {
                    final var name = new Name(
                            api.getId(),
                            entry.getMemberName(),
                            List.of(root.toString()),
                            ""
                    );
                    final var num = new Num(
                            name.getNameId(),
                            Double.valueOf(entry.getStr())
                    );
                    names.put(name.getNameId(), name);
                    final var reference = new Reference(
                            ValueFileType.NUM,
                            null,
                            num
                    );
                    final var value = new Value(
                            api.getId(),
                            name,
                            reference
                    );
                    values.put(name.getNameId(), value);
                }
                case DATE -> {
                    final var name = new Name(
                            api.getId(),
                            entry.getMemberName(), List.of(root.toString()), ""
                    );
                    final var date = new Date(
                            name.getNameId(),
                            LocalDateTime.parse(entry.getDate())
                    );
                    names.put(name.getNameId(), name);
                    final var reference = new Reference(
                            ValueFileType.DATE,
                            null,
                            date
                    );
                    final var value = new Value(
                            api.getId(),
                            name,
                            reference
                    );
                    values.put(name.getNameId(), value);
                }
                case PATH -> {
                    final var name = new Name(
                            api.getId(),
                            entry.getMemberName(), List.of(root.toString()), ""
                    );
                    final var path = new Path(
                            name.getNameId(),
                            entry.getPath()
                    );
                    names.put(name.getNameId(), name);
                    final var reference = new Reference(
                            ValueFileType.PATH,
                            null,
                            path
                    );
                    final var value = new Value(
                            api.getId(),
                            name,
                            reference
                    );
                    values.put(name.getNameId(), value);
                }
                case BOOL -> {
                    final var name = new Name(
                            api.getId(),
                            entry.getMemberName(), List.of(root.toString()), ""
                    );
                    final var bool = new Bool(
                            name.getNameId(),
                            BoolType.valueOf(entry.getBool())
                    );
                    names.put(name.getNameId(), name);
                    final var reference = new Reference(
                            ValueFileType.BOOL,
                            null,
                            bool
                    );
                    final var value = new Value(
                            api.getId(),
                            name,
                            reference
                    );
                    values.put(name.getNameId(), value);
                }
            }
        }


        return data;
    }
}