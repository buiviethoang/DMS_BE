package com.thesis.dms.common;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
@Component
public class CommonResource {
    @Value("${redis.host}")
    private String redisHost;

    @Value("${redis.port}")
    private int redisPort;

    @Value("${permission.url-link}")
    private String urlLink;

    @Value("${image.upload-dir}")
    private String imagePath;

    @Value("${image.thumbnail.width}")
    private int thumbnailWidth;

    @Value("${image.thumbnail.height}")
    private int thumbnailHeight;

    @Value("${file.upload-dir}")
    private String filePath;

    @Value("${websocket.base-path}")
    private String websocketPath;
}
