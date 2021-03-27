package com.fitincontact.exondoc.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

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

}