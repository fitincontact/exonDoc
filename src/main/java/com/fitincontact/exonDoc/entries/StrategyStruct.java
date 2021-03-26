package com.fitincontact.exonDoc.entries;

import com.fitincontact.exonDoc.enums.CharPlaceType;
import com.fitincontact.exonDoc.enums.ParentType;

public class StrategyStruct {
    private ParentType parentType = ParentType.OBJ;
    private CharPlaceType charPlaceTypePoint = CharPlaceType.OBJ_L_CURLY_BRACKET;
    private char ch;

    public StrategyStruct() {
    }

    public ParentType getParent() {
        return parentType;
    }

    public void setParent(ParentType parentType) {
        this.parentType = parentType;
    }

    public CharPlaceType getStrategyPoint() {
        return charPlaceTypePoint;
    }

    public void setStrategyPoint(CharPlaceType charPlaceTypePoint) {
        this.charPlaceTypePoint = charPlaceTypePoint;
    }

    public char getCh() {
        return ch;
    }

    public void setCh(char ch) {
        this.ch = ch;
    }
}