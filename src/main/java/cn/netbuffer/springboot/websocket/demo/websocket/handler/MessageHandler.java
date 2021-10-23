package cn.netbuffer.springboot.websocket.demo.websocket.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

@Slf4j
public class MessageHandler extends AbstractWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        SessionManager.put(session.getId(), session);
        log.info("session[{}] afterConnectionEstablished", session.getId());
        log.info("get WebSocketSession attributes={}",session.getAttributes());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("session[{}] afterConnectionClosed", session.getId());
        SessionManager.remove(session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("session[{}] receive text message={}", session.getId(), message);
        //for test error
        if(message.getPayload().equals("error")){
            throw new RuntimeException(this.getClass().getSimpleName()+".handleTextMessage error");
        }
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
        log.info("session[{}] receive binary message={} length={}", session.getId(), message, message.getPayloadLength());
    }

    @Override
    protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
        log.info("session[{}] receive pong message={}", session.getId(), message);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
    }

}
