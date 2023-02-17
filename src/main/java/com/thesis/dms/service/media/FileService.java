package com.thesis.dms.service.media;

import com.thesis.dms.common.CommonResource;
import com.thesis.dms.exception.CustomException;
import com.thesis.dms.exception.FileStorageException;
import com.thesis.dms.service.BaseService;
import com.thesis.dms.utils.RandomUtils;
import com.thesis.dms.utils.StrUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@Slf4j
public class FileService extends BaseService {
     @Autowired
    private CommonResource commonResource;

     @Transactional
    public String uploadFile(MultipartFile file) throws Exception {
         return storeFile(file);
     }
    public String storeFile(MultipartFile file) {
        File f = new File(commonResource.getFilePath());
        if (!f.exists()) {
            f.mkdirs();
        }

        Path fileStorageLocation = Paths.get(commonResource.getFilePath());
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        fileName = StrUtils.convertToUnsignedLowerCase(fileName);
        fileName = StrUtils.removeSpecialCharactor(fileName);
        String fileExt = "";
        if (fileName.contains(".")) {
            fileExt = fileName.substring(fileName.lastIndexOf("."));
            fileName = fileName.substring(0, fileName.lastIndexOf("."));
        }
        fileName = fileName.replaceAll("[#.]", "") + "_" + RandomUtils.getRandomId() + fileExt;
        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return "/files/" + fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    @Transactional
    public ResponseEntity<?> downloadFile(String fileName) throws CustomException {
        HttpHeaders responseHeader = new HttpHeaders();
        try {
            File file = new File(commonResource.getFilePath()+"/"+fileName);
            byte[] data = FileUtils.readFileToByteArray(file);
            // Set mimeType trả về
            responseHeader.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            // Thiết lập thông tin trả về
            responseHeader.set("Content-disposition", "attachment; filename=" + file.getName());
            responseHeader.setContentLength(data.length);
            InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(data));
            InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
            return new ResponseEntity<>(inputStreamResource, responseHeader, HttpStatus.OK);
        } catch (Exception ex) {
            log.debug("Lỗi tải file pdf: {}",ex);
            throw caughtException(2,"Lỗi không thể tải file!");
        }
    }
}
