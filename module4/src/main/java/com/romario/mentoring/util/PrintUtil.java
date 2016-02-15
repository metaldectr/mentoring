package com.romario.mentoring.util;

import java.util.Formatter;

public class PrintUtil {
    public static String $(String pattern, Object...args){
        return new Formatter().format(pattern, args).toString();
    }
}
