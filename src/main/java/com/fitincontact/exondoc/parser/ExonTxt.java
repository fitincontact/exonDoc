package com.fitincontact.exondoc.parser;

import com.fitincontact.exondoc.parser.entries.Exon;
import com.fitincontact.exondoc.parser.entries.ExonEntry;
import com.fitincontact.exondoc.parser.entries.StrategyStruct;
import com.fitincontact.exondoc.parser.enums.CharPlaceType;
import com.fitincontact.exondoc.parser.enums.ParentType;
import com.fitincontact.exondoc.parser.enums.ParseAction;
import com.fitincontact.exondoc.parser.enums.ValueType;
import org.jetbrains.annotations.Nullable;

import java.util.Stack;


public class ExonTxt {

    @Nullable
    public Exon parse(
            final String collection,
            final String exonString
    ) {
        final var cleanExonStringTemp = exonString.strip();
        //todo if size = 0
        var chPosition = exonString.length() - cleanExonStringTemp.length() - 1;
        if (cleanExonStringTemp.toCharArray()[0] != '{') {
            err(cleanExonStringTemp.toCharArray()[0], chPosition + 1);
        }
        chPosition++;
        //todo if size = 0
        final var cleanExonString = cleanExonStringTemp.substring(1, cleanExonStringTemp.length() - 1);

        final var rootExon = new Exon(collection);
        final var stack = new Stack<ExonEntry>();
        stack.push(rootExon.getExonEntry());

        final StrategyStruct struct = new StrategyStruct();
        for (final char ch : cleanExonString.toCharArray()) {
            chPosition++;
//todo

//            final var tmp = cleanExonString.substring(chPosition, cleanExonString.length() );
//            if (chPosition == cleanExonString.length() - 2) {
//                System.out.println("--stop--");
//            }
//            if (chPosition == 340) {
//                System.out.println("--stop--");
//            }

            struct.setCh(ch);
            ExonTxtStrategy.define(struct);
            final var strategy = struct.getCharPlaceType();
            final var parseAction = ParseAction.charPlaceMapOnParseAction(strategy);
            if (parseAction == null) {
                System.out.println("--null--");
                err(ch, chPosition);
            }
            switch (parseAction) {
                case NEW_MEMBER_NAME -> {
                    final var current = stack.peek();
                    final var member = new ExonEntry(ch);
                    current.add(member);
                    stack.push(member);
                }
                case ASSEMBLE_MEMBER_NAME -> {
                    final var current = stack.peek();
                    current.setMemberName(current.getMemberName() + ch);
                }
                case NEW_OBJ -> {
                    final var current = stack.peek();
                    if (!current.getMemberName().isEmpty() && current.getValueType() != ValueType.ARR) {
                        current.setValueType(ValueType.OBJ);
                    } else {
                        final var obj = new ExonEntry(ValueType.OBJ);
                        current.add(obj);
                        stack.push(obj);
                    }
                }
                case NEW_ARR -> {
                    stack.peek().setValueType(ValueType.ARR);
                }
                case NEW_STR -> {
                    final var current = stack.peek();
                    if (current.getValueType() == ValueType.ARR) {
                        final var str = new ExonEntry(ValueType.STR);
                        current.add(str);
                        stack.push(str);
                    } else {
                        current.setValueType(ValueType.STR);
                    }
                }
                case ASSEMBLE_STR, ASSEMBLE_NUM, ASSEMBLE_DATE, ASSEMBLE_PATH, ASSEMBLE_BOOL -> {
                    stackAssemble(stack, ch);
                }
                case NEW_NUM -> {
                    final var current = stack.peek();
                    current.setNum(ch);
                    current.setValueType(ValueType.NUM);
                }
                case NEW_DATE -> {
                    stack.peek().setValueType(ValueType.DATE);
                }
                case NEW_PATH -> {
                    stack.peek().setValueType(ValueType.PATH);
                }
                case NEW_BOOL -> {
                    final var current = stack.peek();
                    current.setBool(ch);
                    current.setValueType(ValueType.BOOL);
                }
                case BACK -> {
                    stack.pop();

                    switch (stack.peek().getValueType()) {
                        case OBJ -> struct.setParent(ParentType.OBJ);
                        case ARR -> struct.setParent(ParentType.ARR);
                    }

                    //num and bool dont have special end symbol like other primitives
                    if (struct.getParent() == ParentType.OBJ &&
                        (struct.getCharPlaceType() == CharPlaceType.NUM_WHITE_SPACE ||
                         struct.getCharPlaceType() == CharPlaceType.BOOL_WHITE_SPACE)) {
                        struct.setCharPlaceType(CharPlaceType.OBJ_WHITE_SPACE_1);
                    }
                    if (struct.getParent() == ParentType.ARR &&
                        (struct.getCharPlaceType() == CharPlaceType.NUM_WHITE_SPACE ||
                         struct.getCharPlaceType() == CharPlaceType.BOOL_WHITE_SPACE)) {
                        struct.setCharPlaceType(CharPlaceType.ARR_WHITE_SPACE);
                    }

                    if (stack.isEmpty()) {
                        return rootExon;
                    }
                }
                case CONTINUE -> {
                }
                case ERROR -> {
                    err(ch, chPosition);
                }
            }
        }
        return rootExon;
    }

    private Exon err(final char ch, final int place) {
        System.out.println("Non expected symbol: <" +
                           ch +
                           ">, name: <" +
                           Character.getName(ch) +
                           ">, on position: " +
                           place);
        return null;
    }

    private void stackAssemble(
            final Stack<ExonEntry> stack,
            final char ch
    ) {
        final var entryCurrent = stack.peek();

        switch (entryCurrent.getValueType()) {
            case STR -> {
                entryCurrent.setStr(entryCurrent.getStr() + ch);
            }
            case NUM -> {
                entryCurrent.setNum(entryCurrent.getNum() + ch);
            }
            case DATE -> {
                entryCurrent.setDate(entryCurrent.getDate() + ch);
            }
            case PATH -> {
                entryCurrent.setPath(entryCurrent.getPath() + ch);
            }
            case BOOL -> {
                entryCurrent.setBool(entryCurrent.getBool() + ch);
            }

        }
    }

}