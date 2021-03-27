package com.fitincontact.exondoc.parser.entries;

import com.fitincontact.exondoc.parser.enums.CharPlaceType;
import com.fitincontact.exondoc.parser.enums.ParentType;

public class StrategyStruct {
    private ParentType parentType = ParentType.OBJ;
    private CharPlaceType charPlaceType = CharPlaceType.OBJ_L_CURLY_BRACKET;
    private char ch;

    public StrategyStruct() {
    }

    public ParentType getParent() {
        return parentType;
    }

    public void setParent(ParentType parentType) {
        this.parentType = parentType;
    }

    public CharPlaceType getCharPlaceType() {
        return charPlaceType;
    }

    public void setCharPlaceType(CharPlaceType charPlaceTypePoint) {
        this.charPlaceType = charPlaceTypePoint;
    }

    public char getCh() {
        return ch;
    }

    public void setCh(char ch) {
        this.ch = ch;
    }
}