package com.fitincontact.exondoc.api;

import com.fitincontact.exondoc.core.Shell;
import com.fitincontact.exondoc.parser.ExonTxt;

public class ExonDoc {
    public Shell shell() {
        return new Shell();
    }

    public ExonTxt parse() {
        return new ExonTxt();
    }
}