package com.hsman.plugin.mails;

import com.hsman.plugin.io.StringReader;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.regex.Pattern;


public class MailTemplate {
    public int playerUid;
    public String title;
    public String sender;
    public ArrayList<GameItem> items;
    /*
    * This string can be a nature language string like 1y3m2d
    * Support units: y year years m mon months d day days h hour hours min minute minutes s sec seconds
    * You also can combine them unordered like 7d5y6min
    */
    public String delayTime;
    public static String asUrlTag(String urlFormat) {
        String prefix = urlFormat.substring(0, 3);
        if(!prefix.equals("url")) {
            return urlFormat;
        }
        com.hsman.plugin.io.StringReader stringReader = new StringReader(urlFormat.substring(4));
        StringBuilder token = new StringBuilder();
        ArrayList<String> tokens = new ArrayList<>();
        while(true) {
            var cur = stringReader.peek();
            if(cur == 0) {
                break;
            }

            switch (cur) {
                case ',', ')' -> {
                    if (!token.isEmpty()) {
                        tokens.add(token.toString());
                        token.setLength(0);
                    }
                    stringReader.read();
                    continue;
                }

                case '\'' -> {
                    stringReader.read();
                    while (true) {
                        if(stringReader.peek() == '\'') {
                            stringReader.read();
                            break;
                        }

                        token.append(stringReader.read());
                    }
                    continue;
                }

                case ' ' -> {
                    stringReader.read();
                    continue;
                }
            }

            token.append(stringReader.read());
        }

        return String.format("<type=\"%s\" text=\"%s\" href=\"%s\">",tokens.get(1),tokens.get(2),tokens.get(0));
    }

    static Object invokeMethod(String str) {
        MailTemplate template = new MailTemplate();
        int leftParIdx = str.indexOf('(');
        StringBuilder funcName = new StringBuilder(str.substring(0, leftParIdx));
        funcName.setCharAt(0, Character.toUpperCase(funcName.charAt(0)));
        String parseMethodName = funcName.toString().trim();
        try {
            var parseMethod = MailTemplate.class.getDeclaredMethod(parseMethodName, String.class);
            return parseMethod.invoke(template, str);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return str;
        }
    }

    public static String parseContent(String content) {
        StringBuilder parsedContent = new StringBuilder();
        Pattern pattern = Pattern.compile("\\$\\{(\\s*\\w+\\s*\\(.*?\\))}\\s*");
        var funcMatcher = pattern.matcher(content);
        while(funcMatcher.find()) {
            for(int i = 1; i < funcMatcher.groupCount() + 1; i++) {
                var parsedFunc = invokeMethod(funcMatcher.group(i)).toString();
                funcMatcher.appendReplacement(parsedContent, parsedFunc);
            }
        }

        funcMatcher.appendTail(parsedContent);
        return parsedContent.toString();
    }
}
