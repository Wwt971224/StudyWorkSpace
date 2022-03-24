package com.ipinyou.other.util;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class CollectionUtil {

    public static <T> boolean removeIf(List<T> list, Predicate<? super T> filter) {
        if (list == null || list.size() == 0 || filter == null) {
            return true;
        }
        Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()) {
            T t = iterator.next();
            if (filter.test(t)) {
                iterator.remove();
            }
        }
        return true;
    }

}
