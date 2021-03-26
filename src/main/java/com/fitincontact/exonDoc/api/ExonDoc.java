package com.fitincontact.exonDoc.api;

import com.fitincontact.exonDoc.Parser.ExonTxt;
import com.fitincontact.exonDoc.core.Shell;

public class ExonDoc {
    public Shell shell() {
        return new Shell();
    }

    public ExonTxt parse() {
        return new ExonTxt();
    }
}