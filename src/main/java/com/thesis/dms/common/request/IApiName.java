package com.thesis.dms.common.request;

import com.thesis.dms.entity.BaseEntity;

public interface IApiName {
    // Base urls
    String API_V1 = "/api/v1";
    String BASE_API_URL = "/";
    String BASE_GET_ALL = "/get-all";
    // Functional urls
    String AUTHENTICATION = BASE_API_URL + "/auth";
    String USER = BASE_API_URL + "/user";
    String PERMISSION = BASE_API_URL + "/permission";
    String ROLE = BASE_API_URL + "/role";
    String IMAGE = BASE_API_URL + "/image";
    String FILE = BASE_API_URL + "file";
    String PRODUCT = BASE_API_URL + "product";

}
