package com.fitincontact.exonDoc.enums;

import java.util.Set;

public enum ParseAction {
    NEW_MEMBER_NAME(Set.of(CharPlaceType.OBJ_MEMBER_CHARACTER_FIRST)),
    ASSEMBLE_MEMBER_NAME(Set.of(CharPlaceType.OBJ_MEMBER_CHARACTER_AFTER_FIRST)),
    NEW_OBJ(Set.of(CharPlaceType.OBJ_L_CURLY_BRACKET)),
    NEW_ARR(Set.of(CharPlaceType.ARR_L_SQUARE_BRACKET)),
    NEW_STR(Set.of(CharPlaceType.STR_L_APOSTROPHE)),
    ASSEMBLE_STR(Set.of(CharPlaceType.STR_CHARACTER)),
    NEW_NUM(Set.of(CharPlaceType.NUM_DIGIT_FIRST)),
    ASSEMBLE_NUM(Set.of(CharPlaceType.NUM_DIGIT_AFTER_FIRST)),
    NEW_DATE(Set.of(CharPlaceType.DATE_L_QUOTATION_MARKS)),
    ASSEMBLE_DATE(Set.of(CharPlaceType.DATE_CHARACTER)),
    NEW_PATH(Set.of(CharPlaceType.PATH_L_GRAVE_ACCENT)),
    ASSEMBLE_PATH(Set.of(CharPlaceType.PATH_CHARACTER)),
    NEW_BOOL(
            Set.of(CharPlaceType.BOOL_TRUE_T,
                    CharPlaceType.BOOL_FALSE_F,
                    CharPlaceType.BOOL_NIL_N)
    ),
    ASSEMBLE_BOOL(
            Set.of(CharPlaceType.BOOL_TRUE_R,
                    CharPlaceType.BOOL_TRUE_U,
                    CharPlaceType.BOOL_TRUE_E,
                    CharPlaceType.BOOL_FALSE_A,
                    CharPlaceType.BOOL_FALSE_L,
                    CharPlaceType.BOOL_FALSE_S,
                    CharPlaceType.BOOL_FALSE_E,
                    CharPlaceType.BOOL_NIL_I,
                    CharPlaceType.BOOL_NIL_L)
    ),
    BACK(
            Set.of(CharPlaceType.OBJ_R_CURLY_BRACKET,
                    CharPlaceType.ARR_R_SQUARE_BRACKET,
                    CharPlaceType.STR_R_APOSTROPHE,
                    CharPlaceType.NUM_WHITE_SPACE,
                    CharPlaceType.DATE_R_QUOTATION_MARKS,
                    CharPlaceType.PATH_R_GRAVE_ACCENT,
                    CharPlaceType.BOOL_WHITE_SPACE)
    ),
    CONTINUE(
            Set.of(CharPlaceType.OBJ_WHITE_SPACE_1,
                    CharPlaceType.OBJ_WHITE_SPACE_2,
                    CharPlaceType.OBJ_WHITE_SPACE_3,
                    CharPlaceType.ARR_WHITE_SPACE,
                    CharPlaceType.OBJ_COLON)
    ),
    ERROR(Set.of(CharPlaceType.ERROR)),
    ;

    private final Set<CharPlaceType> charPlaceTypeSet;

    ParseAction(Set<CharPlaceType> charPlaceTypeSet) {
        this.charPlaceTypeSet = charPlaceTypeSet;
    }

    public static ParseAction charPlaceMapOnParseAction(final CharPlaceType charPlaceType) {
        for (ParseAction parseAction : ParseAction.values()) {
            if (parseAction.getStrategySet().contains(charPlaceType)) {
                return parseAction;
            }
        }
        return null;
    }

    public Set<CharPlaceType> getStrategySet() {
        return charPlaceTypeSet;
    }
}