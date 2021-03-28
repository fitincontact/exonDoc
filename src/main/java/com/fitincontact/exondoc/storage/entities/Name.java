package com.fitincontact.exondoc.storage.entities;

import java.util.List;

public class Name {
    private final Long id;
    private String exonTxt;
    private String name;
    private List<String> root;

    public Name(Long id, String exonTxt, String name, List<String> root) {
        this.id = id;
        this.exonTxt = exonTxt;
        this.name = name;
        this.root = root;
    }

    public Long getId() {
        return id;
    }

    public String getExonTxt() {
        return exonTxt;
    }

    public void setExonTxt(String exonTxt) {
        this.exonTxt = exonTxt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRoot() {
        return root;
    }

    public void setRoot(List<String> root) {
        this.root = root;
    }
}