package com.thesis.dms.config;

import com.thesis.dms.common.CommonResource;
import com.thesis.dms.common.constant.WebsocketConst;
import com.thesis.dms.web.ws.DmsWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

@EnableWebSocket
@Configuration
public class WebSocketConfig implements WebSocketConfigurer {
    @Autowired
    private CommonResource commonResource;


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new DmsWebSocketHandler(), commonResource.getWebsocketPath()).setAllowedOriginPatterns("*");
    }

    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxBinaryMessageBufferSize(WebsocketConst.WEBSOCKET_MAX_BUFFER_SIZE);
        container.setMaxTextMessageBufferSize(WebsocketConst.WEBSOCKET_MAX_BUFFER_SIZE);
        container.setMaxSessionIdleTimeout(WebsocketConst.WEBSOCKET_TIMEOUT);
        return container;
    }
}
