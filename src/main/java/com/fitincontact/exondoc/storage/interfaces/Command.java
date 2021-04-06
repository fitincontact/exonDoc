package com.fitincontact.exondoc.storage.interfaces;

public interface Command<T> {
    void  insert(final T t);
}