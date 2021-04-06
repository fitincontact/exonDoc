package com.fitincontact.exondoc.parser.entries;

import com.fitincontact.exondoc.parser.enums.ValueType;

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

    public ExonEntry(final ValueType valueType) {
        this.valueType = valueType;
    }

    public ExonEntry(final char ch) {
        this.memberName = String.valueOf(ch);
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(final String memberName) {
        this.memberName = memberName;
    }

    public void setMemberName(final char memberName) {
        this.memberName = String.valueOf(memberName);
    }

    public ValueType getValueType() {
        return valueType;
    }

    public void setValueType(final ValueType valueType) {
        this.valueType = valueType;
    }

    public String getStr() {
        return str;
    }

    public void setStr(final String str) {
        this.str = str;
    }

    public String getNum() {
        return num;
    }

    public void setNum(final String num) {
        this.num = num;
    }

    public void setNum(final char num) {
        this.num = String.valueOf(num);
    }

    public String getDate() {
        return date;
    }

    public void setDate(final String date) {
        this.date = date;
    }

    public String getPath() {
        return path;
    }

    public void setPath(final String path) {
        this.path = path;
    }

    public String getBool() {
        return bool;
    }

    public void setBool(final String bool) {
        this.bool = bool;
    }

    public void setBool(final char bool) {
        this.bool = String.valueOf(bool);
    }

    public List<ExonEntry> getArrAndObjList() {
        return arrAndObjList;
    }

    public void setArrAndObjList(final List<ExonEntry> arrAndObjList) {
        this.arrAndObjList = arrAndObjList;
    }

    public void add(final ExonEntry exonEntry) {
        this.arrAndObjList.add(exonEntry);
    }

    @Override
    public String toString() {
        final var res = new StringBuilder();
        res.append(memberName.equals("") ? "" : memberName + " : ");
        if (ValueType.primitive.contains(valueType)) {
            res.append(str.equals("") ? str : "'" + str + "'\n");
            res.append(num.equals("") ? num : num + "\n");
            res.append(date.equals("") ? date : "\"" + date + "\"\n");
            res.append(path.equals("") ? path : "`" + path + "`\n");
            res.append(bool.equals("") ? bool : bool + "\n");
        } else if (valueType == ValueType.OBJ) {
            res.append("{");
            for (final var member : arrAndObjList) {
                res.append(member);
            }
            res.append("}\n");
        } else if (valueType == ValueType.ARR) {
            res.append("[");
            for (final var member : arrAndObjList) {
                res.append(member);
            }
            res.append("]\n");
        }
        return res.toString();
    }

    public String toStringWithFormat(final int indent) {
        final var res = new StringBuilder();
        res.append(memberName.equals("") ? multiply(indent) : multiply(indent) + memberName + " : ");
        if (ValueType.primitive.contains(valueType)) {
            res.append(str.equals("") ? str : "'" + str + "'\n");
            res.append(num.equals("") ? num : num + "\n");
            res.append(date.equals("") ? date : "\"" + date + "\"\n");
            res.append(path.equals("") ? path : "`" + path + "`\n");
            res.append(bool.equals("") ? bool : bool + "\n");
        } else if (valueType == ValueType.OBJ) {
            res.append("{\n");
            for (final var member : arrAndObjList) {
                res.append(member.toStringWithFormat(indent + 1));
            }
            res.append(multiply(indent) + "}\n");
        } else if (valueType == ValueType.ARR) {
            res.append("[\n");
            for (final var member : arrAndObjList) {
                res.append(member.toStringWithFormat(indent + 1));
            }
            res.append(multiply(indent)).append("]\n");
        }
        return res.toString();
    }

    private String multiply(final int indent) {
        final StringBuilder res = new StringBuilder();
        if (indent > 0) {
            for (int i = 0; i <= indent; i++) {
                res.append("    ");
            }
        }
        return res.toString();
    }

    public String toStringInLine() {
        var txt = toString();
        txt = txt.replace("\n", " ");
        txt = txt.replace("\t", " ");
        return txt;
    }
}