package com.fitincontact.exonDoc.entries;

import com.fitincontact.exonDoc.enums.ValueType;

import java.util.ArrayList;
import java.util.List;

public class ExonEntry {
    private ValueType valueType;
    private String memberName = "";
    private String str = "";
    private String num = "";
    private String date = "";
    private String path = "";
    private String bool = "";
    //members
    //see file exon
    private List<ExonEntry> arrAndObjList = new ArrayList<>();

    public ExonEntry(ValueType valueType) {
        this.valueType = valueType;
    }

    public ExonEntry(final char ch) {
        this.memberName = String.valueOf(ch);
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setMemberName(char memberName) {
        this.memberName = String.valueOf(memberName);
    }

    public ValueType getValueType() {
        return valueType;
    }

    public void setValueType(ValueType valueType) {
        this.valueType = valueType;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setNum(char num) {
        this.num = String.valueOf(num);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getBool() {
        return bool;
    }

    public void setBool(String bool) {
        this.bool = bool;
    }

    public void setBool(char bool) {
        this.bool = String.valueOf(bool);
    }

    public List<ExonEntry> getArrAndObjList() {
        return arrAndObjList;
    }

    public void setArrAndObjList(List<ExonEntry> arrAndObjList) {
        this.arrAndObjList = arrAndObjList;
    }

    public void add(final ExonEntry exonEntry) {
        this.arrAndObjList.add(exonEntry);
    }
}