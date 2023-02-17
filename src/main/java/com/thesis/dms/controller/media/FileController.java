package com.thesis.dms.controller.media;

import com.thesis.dms.common.request.IApiName;
import com.thesis.dms.controller.BaseController;
import com.thesis.dms.entity.ResultEntity;
import com.thesis.dms.service.media.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(IApiName.FILE)
public class FileController extends BaseController {
    @Autowired
    private FileService fileService;

    @PostMapping("/uploadFile")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        try {
            return response(new ResultEntity(1, "Upload file successfully", fileService.uploadFile(file)));
        } catch (Exception ex) {
            return response(error(ex));
        }
    }

    @GetMapping("/downloadFile")
    public ResponseEntity<?> downloadFile(@RequestParam("file") String fileName) {
        try {
            return fileService.downloadFile(fileName);
        } catch (Exception ex) {
            return response(error(ex));
        }
    }
}
