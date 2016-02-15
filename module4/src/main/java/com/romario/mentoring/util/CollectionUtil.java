package com.romario.mentoring.util;

import java.util.Collection;

public class CollectionUtil {
    public static boolean isEmpty(Collection<?> c){
        return c == null || c.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> c){
        return !isEmpty(c);
    }
}
