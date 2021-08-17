package com.fitincontact.exondoc.parser.entries;

import com.fitincontact.exondoc.parser.enums.ValueType;
import com.fitincontact.exondoc.storage.StorageApi;
import com.fitincontact.exondoc.storage.entities.*;
import com.fitincontact.exondoc.storage.enums.BoolType;
import com.fitincontact.exondoc.storage.enums.ValueFileType;
import com.fitincontact.exondoc.storage.interfaces.Primitive;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ExonEntry {
    StorageApi api = new StorageApi();

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
        txt = txt.replace(" }", "");
        return txt;
    }

    public Data convert(final StringBuilder root, final boolean isRoot) {
        final var data = api.getNewData();
        final var names = data.getNames();
        final var values = data.getValues();

        if (isRoot) {
            final var name = new Name(
                    api.getId(),
                    root.toString(),
                    null,
                    this.toString()
            );
            names.put(name.getNameId(), name);
        }

        if (ValueType.primitive.contains(valueType)) {
            final var name = new Name(
                    api.getId(),
                    memberName,
                    List.of(root.toString()),
                    ""
            );
            Primitive primitive = null;
            switch (valueType) {
                case OBJ, ARR -> {
                }
                case STR -> {
                    primitive = new Str(
                            name.getNameId(),
                            this.str
                    );
                }
                case NUM -> {
                    primitive = new Num(
                            name.getNameId(),
                            Double.valueOf(this.num)
                    );
                }
                case DATE -> {
                    primitive = new Date(
                            name.getNameId(),
                            LocalDateTime.parse(this.date)
                    );
                }
                case PATH -> {
                    primitive = new Path(
                            name.getNameId(),
                            this.path
                    );
                }
                case BOOL -> {
                    primitive = new Bool(
                            name.getNameId(),
                            BoolType.parseLiteral(this.bool)
                    );
                }
            }
            names.put(name.getNameId(), name);
            final var reference = new Reference(
                    valueTypeToValueFileType(valueType),
                    null,
                    primitive
            );
            final var value = new Value(
                    api.getId(),
                    name,
                    reference
            );
            values.put(name.getNameId(), value);
        } else if (valueType == ValueType.OBJ) {
            for (final var member : arrAndObjList) {
                data.add(member.convert(root, false));
            }
        } else if (valueType == ValueType.ARR) {
            for (final var member : arrAndObjList) {
                data.add(member.convert(root.append(".").append(memberName), false));
            }
        }
        return data;
    }

    private ValueFileType valueTypeToValueFileType(final ValueType valueType) {
        final ValueFileType valueFileType;
        switch (valueType) {
            case OBJ, ARR -> {
                valueFileType = null;
            }
            case STR -> {
                valueFileType = ValueFileType.STR;
            }
            case NUM -> {
                valueFileType = ValueFileType.NUM;
            }
            case DATE -> {
                valueFileType = ValueFileType.DATE;
            }
            case PATH -> {
                valueFileType = ValueFileType.PATH;
            }
            case BOOL -> {
                valueFileType = ValueFileType.BOOL;
            }
            default -> throw new IllegalStateException("Unexpected value: " + valueType);
        }
        return valueFileType;
    }
}