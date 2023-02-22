package com.thesis.dms.web.ws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.nio.ByteBuffer;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
public class DmsWebSocketHandler extends TextWebSocketHandler {
    private CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // Add new session to the list
        try {
            if (!session.isOpen()) {
                log.warn("ws session not open after connection established, session_id: {}", session.getId());
            }
            sessions.add(session);
            System.out.println("New session connected: " + session.getId());
        } catch (Exception ex) {
            log.error("ERROR after connect to socket with session: {}", session, ex);
        }

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // Remove closed session from the list
        sessions.remove(session);
        if (session.isOpen()) {
            session.close();
        }
        log.info("Websocket Connection Closed: session id {}, close status is {} ", session.getId(), status);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Handle incoming WebSocket messages here
        String payload = message.getPayload();
        System.out.println("Received message: " + payload);

        // Broadcast the message to all connected sessions
        for (WebSocketSession s : sessions) {
            s.sendMessage(message);
        }
    }
}
