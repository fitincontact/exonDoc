package com.fitincontact.exonDoc.core;

import com.fitincontact.exonDoc.Parser.ExonTxt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Shell {
    public void start() {
        final ExonTxt parse = new ExonTxt();
        try {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String request = "";

            final String collection = "";

            while (!request.equals("stop")) {
                request = reader.readLine();

                System.out.println("request : " + request);

                final com.fitincontact.exonDoc.entries.Exon exon = parse.parse(collection, request);


            }
        } catch (IOException ex) {
            System.out.println(ex);
        }


    }


}
