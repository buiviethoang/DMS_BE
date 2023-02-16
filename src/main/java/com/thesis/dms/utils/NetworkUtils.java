
package com.thesis.dms.utils;

import com.thesis.dms.common.constant.DateTimeConst;
import com.thesis.dms.common.enums.HttpMethodType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class NetworkUtils {
    public static String callService(String url, HttpMethodType methodType, Map<String, ?> headers, Object body,
                                     Map<String, ?> queryParams, Map<String, ?> urlVariables) {
        HttpHeaders httpHeaders = new HttpHeaders();
        if (!MapUtils.isNullOrEmpty(headers)) {
            headers.forEach((s, object) -> httpHeaders.set(s, object.toString()));
        }
        if (!httpHeaders.containsKey(HttpHeaders.CONTENT_TYPE)) {
            httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        }

        HttpMethod method = HttpMethod.GET;
        switch (methodType) {
        case GET:
            break;
        case POST:
            method = HttpMethod.POST;
            break;
        case DELETE:
            method = HttpMethod.DELETE;
            break;
        default:
            method = HttpMethod.GET;
        }

        if (urlVariables == null) {
            urlVariables = new HashMap<>();
        }

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        if (queryParams != null) {
            for (Map.Entry<String, ?> entry : queryParams.entrySet()) {
                Object obj = entry.getValue();
                String value;
                if (obj instanceof LocalDate) {
                    LocalDate dateValue = (LocalDate) obj;
                    value = dateValue.format(DateTimeConst.DATE_FORMATTER);
                } else if (obj instanceof LocalDateTime) {
                    LocalDateTime dateValue = (LocalDateTime) obj;
                    value = dateValue.format(DateTimeConst.DATE_TIME_FORMATTER);
                } else {
                    value = obj.toString();
                }
                builder.queryParam(entry.getKey(), value);
            }
        }
        HttpEntity<Object> entity = new HttpEntity<>(GsonUtils.toString(body), httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        log.info("Url calling api: " + builder.build(urlVariables));
        log.info("With body content: " + GsonUtils.toString(body));
        ResponseEntity<String> response = restTemplate.exchange(builder.build(urlVariables), method, entity,
                String.class);
        return response.getBody();
    }

    public static String callService(String url, HttpMethodType methodType, Map<String, Object> headers, Object body,
                                     Map<String, Object> queryParams) {
        return callService(url, methodType, headers, body, queryParams, null);
    }

    public static String callService(String url, HttpMethodType methodType, Map<String, Object> headers, Object body) {
        return callService(url, methodType, headers, body, null);
    }

    public static String callService(String url, HttpMethodType methodType, Map<String, Object> headers) {
        return callService(url, methodType, headers, null);
    }
}
