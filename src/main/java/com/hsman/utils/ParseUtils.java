package com.hsman.utils;

import com.hsman.result.ParseResult;

public class ParseUtils {
    public static ParseResult<Integer> tryParseInt(String val) {
        if(val == null) {
            return ParseResult.getFailedResult();
        }

        boolean suc;
        Integer result = null;
        try {
            result = Integer.parseInt(val);
            suc = true;

        } catch(NumberFormatException ex) {
            suc = false;
        }

        return new ParseResult<>(result, suc);
    }


    public static ParseResult<Double> tryParseDouble(String val) {
        if(val == null) {
            return ParseResult.getFailedResult();
        }

        boolean suc;
        Double result = null;
        try {
            result = Double.parseDouble(val);
            suc = true;

        } catch(NumberFormatException ex) {
            suc = false;
        }

        return new ParseResult<>(result, suc);
    }

    public static ParseResult<Boolean> tryParseBoolean(String val) {
        if(val == null) {
            return ParseResult.getFailedResult();
        }

        boolean suc;
        Boolean result = null;
        ParseResult<Integer> intResult = null;
        try {
            intResult = tryParseInt(val);
            if(intResult.isSuccess()) {
                return new ParseResult<>(intResult.getResult() != 0, true);
            }

            result = Boolean.parseBoolean(val);
            suc = true;

        } catch(NumberFormatException ex) {
            suc = false;
        }

        return new ParseResult<>(result, suc);
    }
}
