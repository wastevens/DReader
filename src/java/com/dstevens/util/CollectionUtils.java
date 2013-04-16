package com.dstevens.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CollectionUtils {

    public static <E> List<E> list(E... elements) {
        return new ArrayList<E>(Arrays.asList(elements));
    }
}
