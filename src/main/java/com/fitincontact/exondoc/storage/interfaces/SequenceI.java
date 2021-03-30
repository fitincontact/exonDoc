package com.fitincontact.exondoc.storage.interfaces;

public interface SequenceI {
    Long getId();
    Long getCurrentId();
    Long getLastSavedId();
}