package com.thesis.dms.controller.media;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.thesis.dms.common.request.IApiName;
import com.thesis.dms.controller.BaseController;
import com.thesis.dms.entity.ResultEntity;
import com.thesis.dms.service.media.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(IApiName.IMAGE)
public class ImageController extends BaseController {

    @Autowired
    ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        try {
            return response(new ResultEntity(1, "Upload image successfully", imageService.upload(file)));
        } catch (Exception ex) {
            return response(error(ex));
        }
    }
}
