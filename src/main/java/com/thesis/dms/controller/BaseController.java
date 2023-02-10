package com.thesis.dms.controller;

import com.thesis.dms.constant.response.IErrorResult;
import com.thesis.dms.converter.CustomDozerBeanMapper;
import com.thesis.dms.converter.CustomDozerJdk8BeanMapper;
import com.thesis.dms.entity.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

@Component
@CrossOrigin(origins = "*")
public class BaseController implements IErrorResult {

    @Autowired
    CustomDozerBeanMapper customDozerBeanMapper;

    @Autowired
    CustomDozerJdk8BeanMapper customDozerJdk8BeanMapper;
    public BaseController() {

    }

    /**
     * Log service running time and response json object
     *
     * @param entity Object data muon chuyen sang json
     * @return {"code":0,"message":"no message","data":{}}
     */
    protected ResponseEntity response(ResultEntity entity) {
        return new ResponseEntity(entity, HttpStatus.OK);
    }

}
