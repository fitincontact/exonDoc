package com.fitincontact.exondoc.storage;

public class Storage {
    Sequence sequence = Sequence.SINGLETON.getInstance();


    private void f() {
        sequence.start();
    }

}