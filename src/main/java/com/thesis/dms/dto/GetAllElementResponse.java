package com.thesis.dms.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetAllElementResponse {
    private Long id;
    private String name;
    private String code;

    public GetAllElementResponse(Long id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }
}
