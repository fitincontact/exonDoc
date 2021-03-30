package com.fitincontact.exondoc.utils;

import com.fitincontact.exondoc.storage.enums.Separator;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Util {

    public static String readFile(String path, Charset encoding) {
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(path));
            return new String(encoded, encoding);
        } catch (IOException ex) {
            System.out.println("IOException :" + ex.getMessage());
        }
        return null;
    }

    public static void writeFile(String path, String csq) {
        try {
            Files.writeString(Paths.get(path), csq);
        } catch (IOException ex) {
            System.out.println("IOException :" + ex.getMessage());
        }

    }

    public static List<String> getRowsFromFile(final String file) {
        var rows =
                Objects.requireNonNull(
                        Util.readFile(
                                file,
                                Charset.defaultCharset()
                        )
                );

        return separator(rows, Separator.END_CORTEGE);
    }

    public static List<String> separator(final String base, Separator separator) {
        var tmp = base;
        final var list = new ArrayList<String>();
        var isEnd = false;

        while (!isEnd) {
            final var end = tmp.indexOf(separator.getSeparator());

            var part = "";
            if (end == -1) {
                part = tmp.substring(0, tmp.length() );
                isEnd = true;
            } else {
                part = tmp.substring(0, end);
                tmp = tmp.substring(end + 1);
            }

            list.add(part);

            System.out.println(part);
        }
        return list;
    }
}