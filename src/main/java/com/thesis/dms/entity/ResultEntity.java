package com.thesis.dms.entity;

import com.thesis.dms.common.constant.MessageConst;
import lombok.Data;

@Data
public class ResultEntity {

    private Integer code;
    private String message;
    private Object data;

    public ResultEntity() {
        this.code = 0;
        this.message = "no message";
    }

    public ResultEntity(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResultEntity returnSuccessResult(Object data) {
        return new ResultEntity(1, MessageConst.SUCCESS, data);
    }
}
