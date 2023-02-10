package com.thesis.dms.constant.response;

import com.thesis.dms.entity.ResultEntity;
import com.thesis.dms.exception.CustomException;
import com.thesis.dms.utils.LogUtils;

public interface IErrorResult {
    /**
     * Create response when exception
     */
    default ResultEntity error(Exception ex) {
        LogUtils.getInstance().error(ex);
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setMessage(ex.getMessage());
        if (ex instanceof CustomException) {
            CustomException customException = (CustomException) ex;
            resultEntity.setCode(customException.getCode());
            resultEntity.setData(customException.getData());
        } else {
            resultEntity.setCode(500);
            resultEntity.setMessage("Internal Server Error " + ex.getMessage());
        }
        return resultEntity;
    }
}
