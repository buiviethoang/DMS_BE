package com.thesis.dms.service.media;

import com.thesis.dms.common.CommonResource;
import com.thesis.dms.exception.FileStorageException;
import com.thesis.dms.service.BaseService;
import com.thesis.dms.utils.FileUtils;
import com.thesis.dms.utils.RandomUtils;
import com.thesis.dms.utils.StrUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import org.imgscalr.Scalr;
@Service
@Slf4j
public class ImageService extends BaseService {

    @Autowired
    private CommonResource commonResource;
    @Transactional
    public Map<String, Object> upload(MultipartFile file) throws Exception {
        Map<String, String> images = storeImage(file);
        if (!images.containsKey("image"))
        {
            throw caughtException("Import image failed");
        }
        String imageLocation = images.get("image");
        String thumbnailLocation = imageLocation;

        if (images.containsKey("thumbnail")) {
            thumbnailLocation = images.get("thumbnail");
        }
        Map<String, Object> imageMap = new HashMap<>();
        imageMap.put("image", imageLocation);
        imageMap.put("thumbnail", thumbnailLocation);

        return imageMap;
    }

    public Map<String, String> storeImage(MultipartFile file) {
        File f = new File(commonResource.getImagePath());
        if (!f.exists()) {
            log.warn("Image path not exist, creating one...");
            f.mkdirs();
        }

        Path imageLocation = Paths.get(commonResource.getImagePath());
        log.info("File uploaded: " + file.getOriginalFilename());
        Map<String, String> images = new HashMap<>();
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        String fileExt = "";
        if (fileName.contains(".")) {
            fileExt = fileName.substring(fileName.lastIndexOf('.'));
            fileName = fileName.substring(0, fileName.lastIndexOf('.'));
            fileName = StrUtils.convertToUnsignedLowerCase(fileName);
            fileName = StrUtils.removeSpecialCharactor(fileName);
        }

        log.info("file Ext " + fileExt);
        if (StrUtils.isNullOrWhiteSpace(fileExt)) {
            fileExt = fileExt + ".png";
        }
        log.info("file Ext " + fileExt);
        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                fileName = "image";
            }

            String image = fileName + "_" + RandomUtils.getRandomId() + fileExt;
            images.put("image", "/images/" + image);

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = imageLocation.resolve(image);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            try {
                String imageThumbnail = fileName + "_" + RandomUtils.getRandomId() + ".png";
                File fileThumbnail = FileUtils.createFile(imageLocation.toString() + "/" + imageThumbnail);
                BufferedImage img2 = ImageIO.read(imageLocation.resolve(image).toFile());
                BufferedImage scaledImg =  Scalr.resize(img2, Scalr.Method.QUALITY,
                        commonResource.getThumbnailWidth(), commonResource.getThumbnailHeight(), Scalr.OP_ANTIALIAS);
                ImageIO.write(scaledImg, "png", fileThumbnail);

                images.put("thumbnail", "/images/" + imageThumbnail);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return images;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

}
