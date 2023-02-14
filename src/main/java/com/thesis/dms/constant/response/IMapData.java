package com.thesis.dms.constant.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IMapData<T> {
    Map<String, Object> getMapData(T entity);

    default List<Map<String, Object>> getMapDatas(Iterable<T> entities) {
        List<Map<String, Object>> mapResults = new ArrayList<>();
        for (T entity : entities) {
            mapResults.add(getMapData(entity));
        }
        return mapResults;
    }
}
