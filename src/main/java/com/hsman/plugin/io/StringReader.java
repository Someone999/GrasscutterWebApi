package com.hsman.plugin.io;

public class StringReader {
    private String underlayingString;
    int pos = -1, len;
    public StringReader(String str) {
        underlayingString = str;
        len = str.length();
    }

    public char read() {
        if(pos >= len) {
            return 0;
        }

        return underlayingString.charAt(++pos);
    }

    public char peek() {
        if(pos + 1 >= len) {
            return 0;
        }

        return underlayingString.charAt(pos + 1);
    }
}
