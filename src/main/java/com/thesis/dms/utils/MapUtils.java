

package com.thesis.dms.utils;

import java.util.Map;

public class MapUtils {
    /**
     * Check whether map is null or empty
     *
     * @param map
     * @return <tt>true</tt> if this map contains no key-value mappings
     */
    public static boolean isNullOrEmpty(Map map) {
        return map == null || map.isEmpty();
    }
}
