package com.fitincontact.exonDoc.Parser;

import com.fitincontact.exonDoc.entries.StrategyStruct;
import com.fitincontact.exonDoc.enums.CharPlaceType;

import java.util.Set;

import static com.fitincontact.exonDoc.enums.CharPlaceType.*;

public class ExonTxtStrategy {

    private static final char LEFT_CURLY_BRACKET = '{';
    private static final char RIGHT_CURLY_BRACKET = '}';
    private static final char COLON = ':';
    private static final char LEFT_SQUARE_BRACKET = '[';
    private static final char RIGHT_SQUARE_BRACKET = ']';
    private static final char APOSTROPHE = '\'';
    private static final char QUOTATION_MARK = '"';
    private static final char GRAVE_ACCENT = '`';

    private static final char bool_true_t = 't';
    private static final char bool_true_r = 'r';
    private static final char bool_true_u = 'u';
    private static final char bool_true_e = 'e';
    private static final char bool_false_f = 'f';
    private static final char bool_false_a = 'a';
    private static final char bool_false_l = 'l';
    private static final char bool_false_s = 's';
    private static final char bool_false_e = 'e';
    private static final char bool_nil_n = 'n';
    private static final char bool_nil_i = 'i';
    private static final char bool_nil_l = 'l';

    private static final Set<Character> dateCharacters = Set.of('-', ' ', 'T', COLON);
    private static final Set<Character> pathCharacters = Set.of('/', ' ', '.');

    /**
     * See exon_steps.png
     *
     * @param struct
     */
    public static void define(final StrategyStruct struct) {
        final var strategyPoint = struct.getCharPlaceType();
        struct.setCharPlaceType(ERROR);
        final var ch = struct.getCh();

        switch (strategyPoint) {
            case OBJ_L_CURLY_BRACKET -> {
                if (ch == RIGHT_CURLY_BRACKET) {
                    struct.setCharPlaceType(CharPlaceType.OBJ_R_CURLY_BRACKET);
                } else if (Character.isWhitespace(ch)) {
                    struct.setCharPlaceType(OBJ_WHITE_SPACE_1);
                } else if (Character.isLetter(ch)) {
                    struct.setCharPlaceType(OBJ_MEMBER_CHARACTER_FIRST);
                }
            }
            case OBJ_R_CURLY_BRACKET,
                    ARR_R_SQUARE_BRACKET,
                    STR_R_APOSTROPHE,
                    NUM_WHITE_SPACE,
                    PATH_R_GRAVE_ACCENT,
                    DATE_R_QUOTATION_MARKS,
                    BOOL_WHITE_SPACE -> {
                defineAfterValueIntoObjAndArr(struct);
            }
            case OBJ_WHITE_SPACE_1 -> {
                if (Character.isWhitespace(ch)) {
                    struct.setCharPlaceType(OBJ_WHITE_SPACE_1);
                } else if (ch == RIGHT_CURLY_BRACKET) {
                    struct.setCharPlaceType(OBJ_R_CURLY_BRACKET);
                } else if (Character.isLetter(ch)) {
                    struct.setCharPlaceType(OBJ_MEMBER_CHARACTER_FIRST);
                }
            }
            case OBJ_MEMBER_CHARACTER_FIRST, OBJ_MEMBER_CHARACTER_AFTER_FIRST -> {
                if (Character.isLetter(ch)) {
                    struct.setCharPlaceType(OBJ_MEMBER_CHARACTER_AFTER_FIRST);
                } else if (Character.isWhitespace(ch)) {
                    struct.setCharPlaceType(OBJ_WHITE_SPACE_2);
                } else if (ch == COLON) {
                    struct.setCharPlaceType(OBJ_COLON);
                }
            }
            case OBJ_WHITE_SPACE_2 -> {
                if (Character.isWhitespace(ch)) {
                    struct.setCharPlaceType(OBJ_WHITE_SPACE_2);
                } else if (ch == COLON) {
                    struct.setCharPlaceType(OBJ_COLON);
                }
            }
            case OBJ_COLON, OBJ_WHITE_SPACE_3 -> {
                if (Character.isWhitespace(ch)) {
                    struct.setCharPlaceType(OBJ_WHITE_SPACE_3);
                } else {
                    struct.setCharPlaceType(defineValueIntoObjAndArr(ch));
                }
            }
            case ARR_L_SQUARE_BRACKET, ARR_WHITE_SPACE -> {
                if (ch == RIGHT_SQUARE_BRACKET) {
                    struct.setCharPlaceType(ARR_R_SQUARE_BRACKET);
                } else if (Character.isWhitespace(ch)) {
                    struct.setCharPlaceType(ARR_WHITE_SPACE);
                } else {
                    struct.setCharPlaceType(defineValueIntoObjAndArr(ch));
                }
            }
            case STR_L_APOSTROPHE -> {
                if (ch == APOSTROPHE) {
                    struct.setCharPlaceType(STR_R_APOSTROPHE);
                } else if (Character.isLetter(ch)) {
                    struct.setCharPlaceType(STR_CHARACTER);
                }
            }
            case STR_CHARACTER -> {
                if (Character.isLetter(ch)) {
                    struct.setCharPlaceType(STR_CHARACTER);
                } else if (ch == APOSTROPHE) {
                    struct.setCharPlaceType(STR_R_APOSTROPHE);
                }
            }
            case NUM_DIGIT_FIRST, NUM_DIGIT_AFTER_FIRST -> {
                if (Character.isDigit(ch)) {
                    struct.setCharPlaceType(NUM_DIGIT_AFTER_FIRST);
                } else if (Character.isWhitespace(ch)) {
                    struct.setCharPlaceType(NUM_WHITE_SPACE);
                }
            }
            case DATE_L_QUOTATION_MARKS -> {
                //todo check format
                if (Character.isDigit(ch) || dateCharacters.contains(ch)) {
                    struct.setCharPlaceType(DATE_CHARACTER);
                }
            }
            case DATE_CHARACTER -> {
                if (Character.isDigit(ch) || dateCharacters.contains(ch)) {
                    struct.setCharPlaceType(DATE_CHARACTER);
                } else if (ch == QUOTATION_MARK) {
                    struct.setCharPlaceType(DATE_R_QUOTATION_MARKS);
                }
            }
            case PATH_L_GRAVE_ACCENT -> {
                if (Character.isLetter(ch) || pathCharacters.contains(ch)) {
                    struct.setCharPlaceType(PATH_CHARACTER);
                }
            }
            case PATH_CHARACTER -> {
                if (Character.isLetter(ch) || pathCharacters.contains(ch)) {
                    struct.setCharPlaceType(PATH_CHARACTER);
                } else if (ch == GRAVE_ACCENT) {
                    struct.setCharPlaceType(PATH_R_GRAVE_ACCENT);
                }
            }
            case BOOL_TRUE_T -> {
                if (ch == bool_true_r) {
                    struct.setCharPlaceType(BOOL_TRUE_R);
                }
            }
            case BOOL_TRUE_R -> {
                if (ch == bool_true_u) {
                    struct.setCharPlaceType(BOOL_TRUE_U);
                }
            }
            case BOOL_TRUE_U -> {
                if (ch == bool_true_e) {
                    struct.setCharPlaceType(BOOL_TRUE_E);
                }
            }
            case BOOL_TRUE_E, BOOL_FALSE_E, BOOL_NIL_L -> {
                if (Character.isWhitespace(ch)) {
                    struct.setCharPlaceType(BOOL_WHITE_SPACE);
                }
            }
            case BOOL_FALSE_F -> {
                if (ch == bool_false_a) {
                    struct.setCharPlaceType(BOOL_FALSE_A);
                }
            }
            case BOOL_FALSE_A -> {
                if (ch == bool_false_l) {
                    struct.setCharPlaceType(BOOL_FALSE_L);
                }
            }
            case BOOL_FALSE_L -> {
                if (ch == bool_false_s) {
                    struct.setCharPlaceType(BOOL_FALSE_S);
                }
            }
            case BOOL_FALSE_S -> {
                if (ch == bool_false_e) {
                    struct.setCharPlaceType(BOOL_FALSE_E);
                }
            }
            case BOOL_NIL_N -> {
                if (ch == bool_nil_i) {
                    struct.setCharPlaceType(BOOL_NIL_I);
                }
            }
            case BOOL_NIL_I -> {
                if (ch == bool_nil_l) {
                    struct.setCharPlaceType(BOOL_NIL_L);
                }
            }
        }
    }

    /**
     * See manual/exon
     *
     * @param ch
     * @return
     */
    private static CharPlaceType defineValueIntoObjAndArr(final char ch) {
        CharPlaceType strategy = ERROR;
        if (Character.isDigit(ch)) {
            strategy = NUM_DIGIT_FIRST;
        } else if (Character.isLetter(ch)) {
            switch (ch) {
                case bool_true_t -> strategy = BOOL_TRUE_T;
                case bool_false_f -> strategy = BOOL_FALSE_F;
                case bool_nil_n -> strategy = BOOL_NIL_N;
            }
        } else {
            switch (ch) {
                case LEFT_CURLY_BRACKET -> strategy = OBJ_L_CURLY_BRACKET;
                case LEFT_SQUARE_BRACKET -> strategy = ARR_L_SQUARE_BRACKET;
                case APOSTROPHE -> strategy = STR_L_APOSTROPHE;
                case QUOTATION_MARK -> strategy = DATE_L_QUOTATION_MARKS;
                case GRAVE_ACCENT -> strategy = PATH_L_GRAVE_ACCENT;
            }
        }
        return strategy;
    }

    private static void defineAfterValueIntoObjAndArr(final StrategyStruct struct) {
        switch (struct.getParent()) {
            case OBJ -> {
                if (Character.isWhitespace(struct.getCh())) {
                    struct.setCharPlaceType(OBJ_WHITE_SPACE_1);
                } else if (struct.getCh() == RIGHT_CURLY_BRACKET) {
                    struct.setCharPlaceType(OBJ_R_CURLY_BRACKET);
                }
            }
            case ARR -> {
                if (Character.isWhitespace(struct.getCh())) {
                    struct.setCharPlaceType(ARR_WHITE_SPACE);
                } else if (struct.getCh() == RIGHT_SQUARE_BRACKET) {
                    struct.setCharPlaceType(ARR_R_SQUARE_BRACKET);
                }
            }
        }
    }
}