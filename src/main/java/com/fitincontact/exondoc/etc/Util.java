package com.fitincontact.exondoc.etc;

import com.fitincontact.exondoc.storage.enums.Separator;
import com.fitincontact.exondoc.storage.interfaces.Primitive;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Util {

    public static String readFile(final String path, final Charset encoding) {
        try {
            final byte[] encoded = Files.readAllBytes(Paths.get(path));
            return new String(encoded, encoding);
        } catch (final IOException ex) {
            System.out.println("IOException :" + ex.getMessage());
        }
        return null;
    }

    public static void writeFile(final String path, final String csq) {
        try {
            Files.writeString(Paths.get(path), csq);
        } catch (final IOException ex) {
            System.out.println("IOException :" + ex.getMessage());
        }

    }

    public static List<String> getRowsFromFile(final String file) {
        final var rows =
                Objects.requireNonNull(
                        Util.readFile(
                                file,
                                Charset.defaultCharset()
                        )
                );
        return separator(rows, Separator.END_CORTEGE);
    }

    public static List<String> separator(final String base, final Separator separator) {
        var tmp = base;
        final var list = new ArrayList<String>();
        var isEnd = false;

        while (!isEnd) {
            final var end = tmp.indexOf(separator.getSeparator());

            var part = "";
            if (end == -1) {
                part = tmp;
                isEnd = true;
            } else {
                part = tmp.substring(0, end);
                tmp = tmp.substring(end + 1);
            }

            list.add(part);

            //System.out.println(part);
        }
        return list;
    }

//    public static List<RawPrimitive> readPrimitive(final String file) {
//        final var rows = getRowsFromFile(file);
//        final var rawPrimitives = new ArrayList<RawPrimitive>();
//        rows.forEach(row -> {
//            final var fields = Util.separator(row, Separator.FIELD);
//            final var rawPrimitive = new RawPrimitive(
//                    Long.valueOf(fields.get(0)),
//                    fields.get(1)
//            );
//            rawPrimitives.add(rawPrimitive);
//        });
//        return rawPrimitives;
//    }

    public static List<Pair<Long, String>> readPrimitive(final String filePath) {
        final var rows = getRowsFromFile(filePath);
        final var rawPrimitives = new ArrayList<Pair<Long, String>>();
        rows.forEach(row -> {
            if(!row.equals("")){
                final var fields = Util.separator(row, Separator.FIELD);
                final Pair<Long, String> rawPrimitive = new Pair(
                        Long.valueOf(fields.get(0)),
                        fields.get(1)
                );
                rawPrimitives.add(rawPrimitive);
            }

        });
        return rawPrimitives;
    }

//    public static void writePrimitive(
//            final Map<Long, Primitive> map,
//            final String filePath
//    ) {
//        final var file = new StringBuilder();
//
//        for (final Map.Entry<Long,  Primitive> entry : map.entrySet()) {
//            file
//                    .append(entry.getValue().getId())
//                    .append(Separator.FIELD.getSeparator())
//                    .append(String.valueOf(entry.getValue().getVal()))//(char[])
//                    .append(Separator.FIELD.getSeparator())
//                    .append(Separator.END_CORTEGE.getSeparator());
//        }
//        Util.writeFile(
//                filePath,
//                file.substring(0, file.length() - 1)
//        );
//    }

    public static void writePrimitive(
            final Map<Long, ? extends Primitive> map,
            final String filePath
    ) {
        final var file = new StringBuilder();

        for (final Map.Entry<Long, ? extends Primitive> entry : map.entrySet()) {
            file
                    .append(entry.getValue().getValId())
                    .append(Separator.FIELD.getSeparator())
                    .append(entry.getValue().getVal())
                    .append(Separator.FIELD.getSeparator())
                    .append(Separator.END_CORTEGE.getSeparator());
        }
        if(!file.toString().equals("")){
            Util.writeFile(
                    filePath,
                    file.substring(0, file.length() - 1)
            );
        }

    }

}